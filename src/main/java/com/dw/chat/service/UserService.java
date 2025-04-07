package com.dw.chat.service;

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


}
