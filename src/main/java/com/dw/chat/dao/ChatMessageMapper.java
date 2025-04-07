package com.dw.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.chat.model.entity.DwcChatMessage;
import com.dw.chat.model.vo.ChatMessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 聊天消息表 Mapper 接口
 * </p>
 *
 * @author dawei
 * @since 2025-04-07
 */
public interface ChatMessageMapper extends BaseMapper<DwcChatMessage> {

    List<ChatMessageVo> queryMessageByChatId(String chatId);

    List<ChatMessageVo> queryLastMessageByChatId(@Param("chatId") String chatId,
                                                 @Param("limit") int limit);

}

