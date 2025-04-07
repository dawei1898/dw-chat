package com.dw.chat.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天会话记录返参
 * 
 * @author dawei
 */
@Data
public class ChatRecordVo implements Serializable {
    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 聊天会话ID */
    private String chatId;

    /** 会话名称 */
    private String chatName;

    /** 所属用户ID */
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /** 是否删除（0-否，1-是） */
    private Integer deleted;


}