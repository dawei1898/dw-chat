package com.dw.chat.model.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息返参
 */
@Data
public class UserVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户昵称 */
    private String name;

    /** 手机号 */
    private Long phone;

    /** 邮箱 */
    private String email;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /** 是否删除（0-否，1-是） */
    private Integer deleted;

}