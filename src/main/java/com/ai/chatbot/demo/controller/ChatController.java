package com.ai.chatbot.demo.controller;

import com.ai.chatbot.demo.dto.response.ReferenceGoals;
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
        ReferenceGoals referenceGoals = this.chatClient.prompt()
                .user(input)
                .call()
                .entity(ReferenceGoals.class);

        return referenceGoals.toString();
    }
}
