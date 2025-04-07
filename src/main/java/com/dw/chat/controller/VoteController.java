package com.dw.chat.controller;

import com.dw.chat.common.constant.ResultMsg;
import com.dw.chat.common.entity.Response;
import com.dw.chat.components.auth.Auth;
import com.dw.chat.model.param.VoteParam;
import com.dw.chat.service.ChatService;
import com.dw.chat.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞/踩记录服务
 *
 * @author dawei
 */

@RestController
@RequestMapping("/vote")
public class VoteController {


    @Autowired
    private VoteService voteServiceImpl;


    /**
     * 保存点赞/踩记录
     */
    @Auth
    @PostMapping("/saveVote")
    public Response<String> saveVote(@RequestBody @Validated VoteParam param){
        String voteId = voteServiceImpl.saveVote(param);
        return Response.success(ResultMsg.SUCCESS, voteId);
    }

}
