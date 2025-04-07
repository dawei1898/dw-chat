package com.dw.chat.model.param;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;


/**
 * 用户登录入参
 *
 * @author dawei
 */
@Data
public class LoginParam implements java.io.Serializable {

    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @NotBlank
    private String username;

    /** 密码 */
    @NotBlank
    private String password;

}