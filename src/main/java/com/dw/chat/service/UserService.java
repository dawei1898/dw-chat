package com.dw.chat.service;

import com.dw.chat.model.param.LoginParam;
import com.dw.chat.model.param.RegisterParam;

/**
 * 用户服务
 *
 * @author dawei
 */
public interface UserService {

    /**
     * 注册用户
     */
    void register(RegisterParam param);

    /**
     * 用户登录
     */
    String login(LoginParam param);


}
