package com.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MessagingStompWebsocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MessagingStompWebsocketApplication.class, args);
        TestMessaging bean = context.getBean(TestMessaging.class);
        bean.sendMessage();

    }

    @Bean
    public List<String> sessions(){
        return new ArrayList<>();
    }
}
