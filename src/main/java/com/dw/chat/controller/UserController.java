package com.dw.chat.controller;

import com.dw.chat.common.entity.Response;
import com.dw.chat.model.param.RegisterParam;
import com.dw.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 *
 * @author dawei
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceImpl;


    /**
     * 注册用户
     */
    @PostMapping("/register")
    public Response<Void> register(@RequestBody @Validated RegisterParam param){
        userServiceImpl.register(param);
        return Response.success();
    }

}
