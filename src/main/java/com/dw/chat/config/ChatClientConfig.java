package com.dw.chat.config;

import lombok.Data;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聊天客户端配置
 *
 * @author dawei
 */
@Data
@Configuration
public class ChatClientConfig {

    /**
     * 聊天客户端
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        // 系统设定
        String sysPrompt = """
                你是一个智能AI助手！
                今天日期是{current_time}。
                """;

        // 日志记录
        SimpleLoggerAdvisor customLoggerAdvisor = new SimpleLoggerAdvisor(
                request -> "[chat request]: " + request.userText(),
                response -> "[chat response]: " + response.getResult(),
                0
        );
        return builder
                .defaultSystem(sysPrompt)
                .defaultAdvisors(customLoggerAdvisor)
                .build();
    }

}
