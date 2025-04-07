package com.dw.chat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 项目启动类
 *
 * @author dawei
 */

@EnableAsync
@SpringBootApplication
public class DwChatApplication {

    private static final Logger logger = LoggerFactory.getLogger(DwChatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DwChatApplication.class, args);
        logger.info("========== DW-CHAT-APP SUCCESS TO START ==========");
    }

}
