package com.dw.chat.controller;

import com.dw.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 聊天服务
 *
 * @author dawei
 */

@RestController
@RequestMapping("/ai")
public class ChatController {


    @Autowired
    private ChatService chatServiceImpl;


    /**
     * 同步响应聊天
     */
    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam(value = "message") String message,
                                    @RequestParam(value = "model", required = false) String model) {
        return chatServiceImpl.chat(message, model);
    }

    /**
     * 流式响应聊天
     */
    @GetMapping(path = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> chatStream(@RequestParam(value = "message") String message,
                                                @RequestParam(value = "model", required = false) String model) {
        return chatServiceImpl.chatStream(message, model);
    }

}
