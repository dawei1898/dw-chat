package com.dw.chat.service.impl;

import com.dw.chat.common.utils.UserContextHolder;
import com.dw.chat.dao.ChatMessageMapper;
import com.dw.chat.model.entity.DwcChatMessage;
import com.dw.chat.model.vo.ChatMessageVo;
import com.dw.chat.model.vo.VoteRecordVo;
import com.dw.chat.service.MessageService;
import com.dw.chat.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聊天消息服务
 *
 * @author dawei
 */

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private VoteService voteServiceImpl;

    /**
     * 新增聊天消息
     */
    @Override
    public String addMessage(DwcChatMessage message) {
        if (message == null) {
            return "";
        }
        chatMessageMapper.insert(message);
        return String.valueOf(message.getMsgId());
    }



    /**
     * 查询聊天消息列表
     */
    @Override
    public List<ChatMessageVo> queryMessageList(String chatId) {
        List<ChatMessageVo> messageVoList = chatMessageMapper.queryMessageByChatId(chatId);
        if (CollectionUtils.isEmpty(messageVoList)) {
            return messageVoList;
        }
        // 封装点赞/踩记录
        List<String> msgIds = messageVoList.stream().map(ChatMessageVo::getMsgId).collect(Collectors.toList());
        String userId = String.valueOf(UserContextHolder.getUserId());
        List<VoteRecordVo> voteRecordVoList = voteServiceImpl.queryVoteList(msgIds, userId);
        Map<String, String> voteMap = voteRecordVoList.stream()
                .collect(Collectors.toMap(VoteRecordVo::getContentId,
                        VoteRecordVo::getVoteType, (k1, k2) -> k1));
        messageVoList.forEach(m -> m.setVoteType(voteMap.get(m.getMsgId())));
        //log.info("messageVoList:{}", JSON.toJSONString(messageVoList));
        return messageVoList;
    }

    /**
     * 查询最近聊天消息列表
     */
    @Override
    public List<ChatMessageVo> queryLastMessageList(String chatId, int limit) {
        return chatMessageMapper.queryLastMessageByChatId(chatId, limit);
    }

}
