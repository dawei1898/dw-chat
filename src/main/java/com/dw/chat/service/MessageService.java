package com.dw.chat.service;

import com.dw.chat.model.entity.DwcChatMessage;
import com.dw.chat.model.vo.ChatMessageVo;

import java.util.List;

/**
 * 聊天消息服务
 *
 * @author dawei
 */

public interface MessageService {


    /**
     * 新增聊天消息
     */
    String addMessage(DwcChatMessage message);

    /**
     * 查询聊天消息列表
     */
    List<ChatMessageVo> queryMessageList(String chatId);

    /**
     * 查询最近聊天消息列表
     */
    List<ChatMessageVo> queryLastMessageList(String chatId, int limit);



}
