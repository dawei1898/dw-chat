package com.dw.chat.common.utils;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.dw.chat.components.auth.LoginUser;

/**
 * 线程持有登录用户信息
 *
 * @author dawei
 */

public class UserContextHolder {

    /**
     * 支持父子线程之间的数据传递
     * 配合 TtlRunnable 使用
     */
    public static final TransmittableThreadLocal<LoginUser> USER_CONTEXT = new TransmittableThreadLocal<>();


    public static void setUser(LoginUser loginUser){
        if (loginUser != null) {
            USER_CONTEXT.set(loginUser);
        }
    }

    public static LoginUser getUser(){
       return USER_CONTEXT.get();
    }

    public static void remove(){
        USER_CONTEXT.remove();
    }

    public static Long getUserId(){
        LoginUser loginUser = getUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

    public static String getTokenId(){
        LoginUser loginUser = getUser();
        return loginUser == null ? "" : loginUser.getTokenId();
    }

}
