package com.dw.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DwChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwChatApplication.class, args);
        //log.info("========== DW-CHAT-APP SUCCESS TO START ==========");
    }

}
