package com.dw.chat.model.param;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 注册用户入参
 *
 * @author dawei
 */
@Data
public class RegisterParam implements Serializable {

    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @NotBlank
    private String username;

    /** 密码 */
    @NotBlank
    private String password;

    /** 邮箱 */
    @NotBlank
    private String email;

    /** 验证码 */
    private String code;


}