package com.dw.chat.model.param;

import com.dw.chat.common.entity.PageParam;
import lombok.Data;

import java.io.Serial;

/**
 * 聊天会话入参
 */
@Data
public class ChatParam extends PageParam {

    /** 会话Id */
    private String chatId;

    /** 会话名称 */
    private String chatName;

}
