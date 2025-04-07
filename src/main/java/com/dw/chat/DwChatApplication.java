package com.dw.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 项目启动类
 *
 * @author dawei
 */

@Slf4j
@EnableAsync
@SpringBootApplication
public class DwChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwChatApplication.class, args);
        log.info("========== DW-CHAT-APP SUCCESS TO START ==========");
    }

}
