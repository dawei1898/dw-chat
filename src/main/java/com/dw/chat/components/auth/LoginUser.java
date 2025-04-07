package com.dw.chat.components.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


/**
 * 登录用户信息
 *
 * @author dawei
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements java.io.Serializable {

    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户唯一标识 */
    private String tokenId;

    /** 用户id */
    private Long userId;

    /** 用户名 */
    private String username;

    /** 登录时间 */
    private Long loginTime;

    /** 过期时间 */
    private Long expireTime;

    /** 登录IP地址 */
    private String ipaddr;

}