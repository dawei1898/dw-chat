package com.dw.chat.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dw.chat.common.constant.ChatConstant;
import com.dw.chat.common.entity.PageResult;
import com.dw.chat.common.enums.ContentTypeEnum;
import com.dw.chat.common.enums.ModelEnum;
import com.dw.chat.common.enums.MsgRoleEnum;
import com.dw.chat.common.enums.MsgTypeEnum;
import com.dw.chat.common.utils.AddressUtil;
import com.dw.chat.common.utils.DateUtil;
import com.dw.chat.common.utils.UserContextHolder;
import com.dw.chat.common.utils.ValidateUtil;
import com.dw.chat.dao.ChatRecordMapper;
import com.dw.chat.model.entity.DwcChatMessage;
import com.dw.chat.model.entity.DwcChatRecord;
import com.dw.chat.model.param.ChatParam;
import com.dw.chat.model.param.MessageParam;
import com.dw.chat.model.param.StreamChatParam;
import com.dw.chat.model.vo.ChatMessageVo;
import com.dw.chat.model.vo.ChatRecordVo;
import com.dw.chat.model.vo.TempMessage;
import com.dw.chat.service.ChatService;
import com.dw.chat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天服务
 *
 * @author dawei
 */

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {


    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Autowired
    private MessageService messageServiceImpl;

    private static final ConcurrentHashMap<Long, TempMessage> MESSAGE_CACHE = new ConcurrentHashMap<>();




    /**
     * 保存聊天会话
     */
    @Override
    public String saveChat(ChatParam param) {
        ValidateUtil.isEmpty(param.getChatName(), "会话名称不能为空!");
        DwcChatRecord dwcChatRecord = new DwcChatRecord();
        dwcChatRecord.setChatId(param.getChatId());
        dwcChatRecord.setChatName(param.getChatName());
        dwcChatRecord.setUserId(UserContextHolder.getUserId());

        if (StringUtils.isEmpty(dwcChatRecord.getChatId())) {
            chatRecordMapper.insert(dwcChatRecord);
        } else {
            DwcChatRecord exist = chatRecordMapper.selectById(dwcChatRecord.getChatId());
            if (exist == null) {
                chatRecordMapper.insert(dwcChatRecord);
            } else {
                chatRecordMapper.updateById(dwcChatRecord);
            }
        }
        return dwcChatRecord.getChatId();
    }

    /**
     * 删除聊天会话
     */
    @Override
    public int deleteChat(String chatId) {
        if (StringUtils.isNotEmpty(chatId)) {
            return chatRecordMapper.deleteById(chatId);
        }
        return 0;
    }

    /**
     * 分页查询聊天会话
     */
    @Override
    public PageResult<ChatRecordVo> queryChatPage(ChatParam param) {
        ValidateUtil.isNull(param, "参数不能为空!");
        QueryWrapper<DwcChatRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", UserContextHolder.getUserId());
        if (StringUtils.isNotBlank(param.getChatName())) {
            queryWrapper.like("chat_name", param.getChatName());
        }
        Page<DwcChatRecord> page = new Page<>(param.getPageNum(), param.getPageSize());
        chatRecordMapper.selectPage(page, queryWrapper);

        List<ChatRecordVo> chatRecordVoList = JSON.parseArray(JSON.toJSONString(page.getRecords()), ChatRecordVo.class);
        chatRecordVoList.sort(Comparator.comparing(ChatRecordVo::getCreateTime).reversed());
        int total = Integer.parseInt(String.valueOf(page.getTotal()));
        return PageResult.build(param.getPageNum(), param.getPageSize(), total, chatRecordVoList);
    }

    /**
     * 查询聊天消息列表
     */
    @Override
    public List<ChatMessageVo> queryMessageList(String chatId) {
        return messageServiceImpl.queryMessageList(chatId);
    }


    /**
     * 保存提问消息
     */
    @Override
    public String addMessage(MessageParam param) {
        String userId = String.valueOf(UserContextHolder.getUserId());
        String chatId = param.getChatId();
        String content = param.getContent();
        // 保存消息
        DwcChatMessage addChatMessage = DwcChatMessage.builder()
                .rawMsgId(IdUtil.simpleUUID())
                .chatId(chatId)
                .type(MsgTypeEnum.USER.getCode())
                .role(MsgRoleEnum.USER.getName())
                .contentType(ContentTypeEnum.TEXT.getCode())
                .content(content)
                .tokens(content.length())
                .createUser(userId)
                .build();
        messageServiceImpl.addMessage(addChatMessage);
        return String.valueOf(addChatMessage.getMsgId());
    }

    /**
     * 发起流式聊天
     */
    @Override
    public Flux<TempMessage> streamChat(StreamChatParam param) {
        log.info("remoteIp:{}, streamChat param:{}",
                AddressUtil.getRemoteIP(), JSON.toJSONString(param));
        Flux<TempMessage> errorFlux = verityStreamChatParam(param);
        if (errorFlux != null) return errorFlux;
        if (StringUtils.isEmpty(param.getModelId())) {
            param.setModelId(param.isOpenReasoning()
                    ? ModelEnum.DEEPSEEK_R1.getName()
                    : ModelEnum.DEEPSEEK_V3.getName());
        }

        try {
            String userId = String.valueOf(UserContextHolder.getUserId());
            Prompt prompt = buildPrompt(param, userId);
            String chatId = param.getChatId();
            String modelId = param.getModelId();
            Long respMsgId = IdUtil.getSnowflakeNextId();
            log.info("Stream chat prompt: {}", JSON.toJSONString(prompt));
            return chatClient.prompt(prompt)
                    .system(s -> s.param(ChatConstant.CURRENT_TIME, DateUtil.getCurrentDateTime()))
                    .stream()
                    .chatResponse()
                    .flatMapSequential(r -> handleFluxResponse(r, userId, chatId, respMsgId, modelId));
        } catch (Exception e) {
            log.error("failed to streamChat.", e);
            TempMessage errorMessage = TempMessage.builder()
                    .errorMsg(e.getMessage())
                    .finished(true)
                    .build();
            return Flux.just(errorMessage);
        }
    }

    private Prompt buildPrompt(StreamChatParam param, String userId) {
        String chatId = param.getChatId();
        String content = param.getContent();
        String modelId = param.getModelId();

        // 保存消息
        DwcChatMessage addChatMessage = DwcChatMessage.builder()
                .rawMsgId(IdUtil.simpleUUID())
                .chatId(chatId)
                .type(MsgTypeEnum.USER.getCode())
                .role(MsgRoleEnum.USER.getName())
                .contentType(ContentTypeEnum.TEXT.getCode())
                .content(content)
                .tokens(content.length())
                .createUser(userId)
                .build();
        messageServiceImpl.addMessage(addChatMessage);

        // 历史对话
        List<Message> messages = new ArrayList<>();
        List<ChatMessageVo> lastMessageList = messageServiceImpl
                .queryLastMessageList(chatId, ChatConstant.CONTEXT_COUNT * 2 + 1);
        if (CollectionUtils.isNotEmpty(lastMessageList)) {
            lastMessageList.sort(Comparator.comparing(ChatMessageVo::getCreateTime));
            lastMessageList.forEach(m -> {
                Message message = MsgRoleEnum.USER.getName().equals(m.getRole())
                        ? new UserMessage(m.getContent()) : new AssistantMessage(m.getContent());
                messages.add(message);
            });
        }

        if (StringUtils.isNotBlank(modelId)) {
            // 选择模型
            ChatOptions chatOptions = ChatOptions.builder().model(modelId).build();
            return new Prompt(messages, chatOptions);
        }
        return new Prompt(messages);
    }

    private Flux<TempMessage> handleFluxResponse(ChatResponse resp, String userId,
                                                 String chatId, Long respMsgId, String modelId) {
        log.debug("response: {}", JSON.toJSONString(resp));
        AssistantMessage output = resp.getResult().getOutput();
        String rawMsgId = String.valueOf(output.getMetadata().get("id"));
        boolean finished = Objects.equals(ChatConstant.FINISH_MSG, resp.getResult().getMetadata().getFinishReason());

        if (!finished) {
            // 缓存消息
            // 思考消息
            String reasoningContent = String.valueOf(output.getMetadata().get("reasoningContent"));
            if (StringUtils.isNotEmpty(reasoningContent)) {
                TempMessage tempMessage = MESSAGE_CACHE.computeIfAbsent(respMsgId, k -> new TempMessage());
                tempMessage.setRawMsgId(rawMsgId);
                tempMessage.getReasoningContentBuilder().append(reasoningContent);
                tempMessage.setRole(output.getMessageType().name().toLowerCase());
            }
            // 回答消息
            if (StringUtils.isNotEmpty(output.getText())) {
                TempMessage tempMessage = MESSAGE_CACHE.computeIfAbsent(respMsgId, k -> new TempMessage());
                tempMessage.setRawMsgId(rawMsgId);
                tempMessage.getContentBuilder().append(output.getText());
                tempMessage.setRole(output.getMessageType().name().toLowerCase());
            }
        } else {
            // 结束后保存消息
            TempMessage tempMessage = MESSAGE_CACHE.remove(respMsgId);
            if (tempMessage != null) {
                String respContent = tempMessage.getContentBuilder().toString();
                String reasoningContent = tempMessage.getReasoningContentBuilder().toString();
                if (StringUtils.isNotEmpty(respContent)) {
                    DwcChatMessage addChatMessage = DwcChatMessage.builder()
                            .msgId(respMsgId)
                            .rawMsgId(tempMessage.getRawMsgId())
                            .chatId(chatId)
                            .type(MsgTypeEnum.AI.getCode())
                            .role(tempMessage.getRole())
                            .modelId(modelId)
                            .modelGroup("")
                            .contentType(ContentTypeEnum.TEXT.getCode())
                            .content(respContent)
                            .reasoningContent(reasoningContent)
                            .tokens(respContent.length())
                            .createUser(userId)
                            .build();
                    messageServiceImpl.addMessage(addChatMessage);
                }
            }
        }

        // 封装响应消息
        TempMessage respMessage = TempMessage.builder()
                .msgId(respMsgId)
                .chatId(chatId)
                .content(output.getText())
                .reasoningContent(String.valueOf(output.getMetadata().get("reasoningContent")))
                .type(MsgTypeEnum.AI.getCode())
                .modelId(modelId)
                .finished(finished)
                .build();
        return Flux.just(respMessage);
    }

    private static Flux<TempMessage> verityStreamChatParam(StreamChatParam param) {
        String errorMsg = null;
        if (param == null) {
            errorMsg = "参数不能为空!";
        }
        if (param != null && StringUtils.isEmpty(param.getChatId())) {
            errorMsg = "chatId不能为空!";
        }
        if (param != null && StringUtils.isEmpty(param.getContent())) {
            errorMsg = "content不能为空!";
        }
        if (StringUtils.isNotEmpty(errorMsg)) {
            TempMessage errorMessage = TempMessage.builder()
                    .errorMsg(errorMsg)
                    .finished(true)
                    .build();
            return Flux.just(errorMessage);
        }
        return null;
    }


}
