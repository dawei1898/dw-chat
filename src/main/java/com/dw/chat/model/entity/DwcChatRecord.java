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
 * 聊天会话记录表
 * </p>
 *
 * @author dawei
 * @since 2025-04-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dwc_chat_record")
public class DwcChatRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 聊天会话ID
     */
    @TableId(value = "chat_id", type = IdType.ASSIGN_UUID)
    private String chatId;

    /**
     * 会话名称
     */
    private String chatName;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除（0-否，1-是）
     */
    @TableLogic
    private Integer deleted;
}
