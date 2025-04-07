package com.dw.chat.service;

import com.dw.chat.model.param.LoginParam;
import com.dw.chat.model.param.RegisterParam;
import com.dw.chat.model.vo.UserVo;

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

    /**
     * 退出登录
     */
    void logout();

    /**
     * 查询用户信息
     */
    UserVo queryUser(Long userId);

}
