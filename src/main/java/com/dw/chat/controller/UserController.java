package com.dw.chat.controller;

import com.dw.chat.common.constant.ResultMsg;
import com.dw.chat.common.entity.Response;
import com.dw.chat.common.utils.UserContextHolder;
import com.dw.chat.components.auth.Auth;
import com.dw.chat.components.log.Log;
import com.dw.chat.model.param.LoginParam;
import com.dw.chat.model.param.RegisterParam;
import com.dw.chat.model.vo.UserVo;
import com.dw.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Log
    @PostMapping("/register")
    public Response<Void> register(@RequestBody @Validated RegisterParam param){
        userServiceImpl.register(param);
        return Response.success();
    }

    /**
     * 用户登录
     */
    @Log
    @PostMapping("/login")
    public Response<String> login(@RequestBody @Validated LoginParam param){
        String token = userServiceImpl.login(param);
        return Response.success(ResultMsg.SUCCESS, token);
    }

    /**
     * 退出登录
     */
    @Log
    @Auth
    @DeleteMapping("/logout")
    public Response<Void> logout(){
        userServiceImpl.logout();
        return Response.success();
    }

    /**
     * 查询用户信息
     */
    @Log
    @Auth
    @GetMapping("/queryUser")
    public Response<UserVo> queryUser(){
        Long userId = UserContextHolder.getUserId();
        UserVo userVo = userServiceImpl.queryUser(userId);
        return Response.success(userVo);
    }

}
