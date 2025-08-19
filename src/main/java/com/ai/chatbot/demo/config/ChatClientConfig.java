package com.ai.chatbot.demo.config;

import com.ai.chatbot.demo.template.PromptTemplates;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem(PromptTemplates.DEFAULT_SYSTEM)
                .build();
    }
}
