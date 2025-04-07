package com.dw.chat.components.auth;

/**
 * 鉴权常量
 *
 * @author dawei
 */
public class AuthConstant {

    public static final String TOKEN_KEY = "Authorization";

    public static final String TOKEN_VALUE_PREFIX = "Bearer ";

    /** 令牌秘钥 */
    public final static String SECRET = "ki9ijeffredsklm9iujnb0o8y5iuh98jnju76o9dsx3wqasdfguy76g";

    /** * 用户ID字段 */
    public static final String USER_ID = "user_id";

    /** 登录用户 */
    public static final String LOGIN_USER = "login_user";

    /** token失效时间(ms) */
    public static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;


    public static final int AUTH_ORDER = 1;

}
