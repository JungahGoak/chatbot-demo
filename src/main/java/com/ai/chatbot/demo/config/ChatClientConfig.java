package com.ai.chatbot.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("사람들이 목표를 입력하면 그걸 달성하기 위한 할 일을 추천하는 챗봇이야")
                .build();
    }
}
