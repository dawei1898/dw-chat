package com.dw.chat.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息返参
 * 
 * @author dawei
 */
@Data
public class ChatMessageVo implements Serializable {
    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private String msgId;

    /** 原始消息ID */
    private String rawMsgId;

    /** 聊天会话ID */
    private String chatId;

    /** 消息类型（1-用户提问，2-机器回答） */
    private String type;

    /** 角色（user-用户，assistant-AI助手） */
    private String role;

    /** 消息内容格式（text-文本，image-图像） */
    private String contentType;

    /** 消息内容 */
    private String content;

    /** 思考内容 */
    private String reasoningContent;

    /** 消耗token数 */
    private Integer tokens;

    /** 模型ID */
    private String modelId;

    /** 模型厂商 */
    private String modelGroup;

    /** up-点赞，down-点踩 */
    private String voteType;

    /** 创建人ID */
    private String createUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /** 是否删除（0-否，1-是） */
    private Integer deleted;


}