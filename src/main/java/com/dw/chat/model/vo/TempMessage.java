package com.dw.chat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * AI返回消息
 * 
 *  @author dawei
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempMessage implements Serializable {
    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 是否结束 */
    private Boolean finished;

    /** 错误描述 */
    private String errorMsg;

    /** 消息ID */
    private Long msgId;

    /** 原始消息ID */
    private String rawMsgId;

    /** 聊天会话ID */
    private String chatId;

    /** 消息类型（1-用户提问，2-机器回答） */
    private String type;

    /** 角色（user-用户，assistant-AI助手） */
    private String role;

    /** 模型ID */
    private String modelId;

    /** 消息内容 */
    private String content;

    /** 消息内容 */
    private StringBuilder contentBuilder = new StringBuilder();

    /** 思考内容 */
    private String reasoningContent;

    /** 思考内容 */
    private StringBuilder reasoningContentBuilder = new StringBuilder();


}