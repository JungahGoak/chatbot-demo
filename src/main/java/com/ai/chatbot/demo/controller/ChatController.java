package com.ai.chatbot.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    String generation(@RequestParam String input) {
        try {
            return this.chatClient.prompt()
                    .user(input)
                    .call()
                    .content();
        } catch (Exception e) {
            return "오류 발생: " + e.getMessage();
        }
    }
}
