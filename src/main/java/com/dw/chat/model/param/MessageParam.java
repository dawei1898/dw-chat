package com.dw.chat.model.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 聊天消息入参
 */
@Data
public class MessageParam {

    @NotBlank
    private String chatId;

    @NotBlank
    private String content;

}
