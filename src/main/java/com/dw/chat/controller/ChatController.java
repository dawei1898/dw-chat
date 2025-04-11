package com.dw.chat.controller;

import com.dw.chat.common.constant.ResultMsg;
import com.dw.chat.common.entity.PageResult;
import com.dw.chat.common.entity.Response;
import com.dw.chat.components.auth.Auth;
import com.dw.chat.model.param.ChatParam;
import com.dw.chat.model.param.MessageParam;
import com.dw.chat.model.param.StreamChatParam;
import com.dw.chat.model.vo.ChatMessageVo;
import com.dw.chat.model.vo.ChatRecordVo;
import com.dw.chat.model.vo.TempMessage;
import com.dw.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.List;

/**
 * 聊天服务
 *
 * @author dawei
 */
@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController {


    @Autowired
    private ChatService chatServiceImpl;


    /**
     * 保存聊天会话
     */
    @Auth
    @PostMapping("/saveChat")
    public Response<String> saveChat(@RequestBody ChatParam param){
        String chatId = chatServiceImpl.saveChat(param);
        return Response.success(ResultMsg.SUCCESS, chatId);
    }

    /**
     * 删除聊天会话
     */
    @Auth
    @DeleteMapping("/deleteChat/{chatId}")
    public Response<Void> deleteChat(@PathVariable(name = "chatId")  String chatId){
        int i = chatServiceImpl.deleteChat(chatId);
        return Response.success(i);
    }


    /**
     * 分页查询聊天会话
     */
    @Auth
    @PostMapping("/queryChatPage")
    public Response<PageResult<ChatRecordVo>> queryChatPage(@RequestBody ChatParam param){
        PageResult<ChatRecordVo> page = chatServiceImpl.queryChatPage(param);
        return Response.success(page);
    }

    /**
     * 查询聊天消息列表
     */
    @Auth
    @GetMapping("/queryMessageList/{chatId}")
    public Response<List<ChatMessageVo>> queryMessageList(@PathVariable(name = "chatId") String chatId){
        List<ChatMessageVo> messageVoList = chatServiceImpl.queryMessageList(chatId);
        return Response.success(messageVoList);
    }

    /**
     * 保存提问消息
     */
    @Auth
    @PostMapping("/addMessage")
    public Response<String> addMessage(@RequestBody @Validated MessageParam param){
        String msgId = chatServiceImpl.addMessage(param);
        return Response.success(msgId);
    }


    /**
     * 发起流式聊天
     */
    @Auth
    @PostMapping(path = "/streamChat" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TempMessage> streamChat(@RequestBody StreamChatParam param){
        return chatServiceImpl.streamChat(param);
    }

}
