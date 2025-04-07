package com.dw.chat.service;

import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 聊天服务
 *
 * @author dawei
 */
public interface ChatService {

    Map<String, String> chat(String message, String model);

    Flux<Map<String, String>> chatStream(String message, String model);

}
