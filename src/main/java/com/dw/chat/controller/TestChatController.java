package com.dw.chat.controller;

import com.dw.chat.service.impl.TestChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 测试聊天服务
 *
 * @author dawei
 */

@RestController
@RequestMapping("/test")
public class TestChatController {


    @Autowired
    private TestChatService testChatService;


    /**
     * 同步响应聊天
     */
    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam(value = "message") String message,
                                    @RequestParam(value = "model", required = false) String model) {
        return testChatService.chat(message, model);
    }

    /**
     * 流式响应聊天
     */
    @GetMapping(path = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, String>> chatStream(@RequestParam(value = "message") String message,
                                                @RequestParam(value = "model", required = false) String model) {
        return testChatService.chatStream(message, model);
    }

}
