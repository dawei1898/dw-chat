package com.dw.chat.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * <p>
 * 聊天消息表
 * </p>
 *
 * @author dawei
 * @since 2025-04-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dwc_chat_message")
public class DwcChatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "msg_id", type = IdType.ASSIGN_ID)
    private Long msgId;

    /**
     * 原始消息ID
     */
    private String rawMsgId;

    /**
     * 聊天会话ID
     */
    private String chatId;

    /**
     * 消息类型（1-用户提问，2-机器回答）
     */
    private String type;

    /**
     * 角色（user-用户，assistant-AI助手）

     */
    private String role;

    /**
     * 消息内容格式（text-文本，image-图像）
     */
    private String contentType;

    /**
     * 消息内容
     */
    private String content;

    /** 思考内容 */
    private String reasoningContent;

    /**
     * 消耗token数
     */
    private Integer tokens;

    /**
     * 模型厂商
     */
    private String modelGroup;

    /**
     * 模型ID
     */
    private String modelId;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 是否删除（0-否，1-是）
     */
    @TableLogic
    private Integer deleted;
}
