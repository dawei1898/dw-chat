package com.dw.chat.service;

import com.dw.chat.common.entity.PageResult;
import com.dw.chat.model.param.ChatParam;
import com.dw.chat.model.param.MessageParam;
import com.dw.chat.model.param.StreamChatParam;
import com.dw.chat.model.vo.ChatMessageVo;
import com.dw.chat.model.vo.ChatRecordVo;
import com.dw.chat.model.vo.TempMessage;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 聊天服务
 *
 * @author dawei
 */

public interface ChatService {

    /**
     * 保存聊天会话
     */
    String saveChat(ChatParam param);

    /**
     * 删除聊天会话
     */
    int deleteChat(String chatId);


    /**
     * 分页查询聊天会话
     */
    PageResult<ChatRecordVo> queryChatPage(ChatParam chatParam);

    /**
     * 查询聊天消息列表
     */
    List<ChatMessageVo> queryMessageList(String chatId);

    /**
     * 保存提问消息
     */
    String addMessage(MessageParam param);

    /**
     * 发起流式聊天
     */
    Flux<TempMessage> streamChat(StreamChatParam param);

}
