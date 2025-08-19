package com.ai.chatbot.demo.controller;

import com.ai.chatbot.demo.dto.response.ReferenceGoals;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @GetMapping("/ai")
    String generation(@RequestParam String input) {
        ReferenceGoals referenceGoals = chatClient.prompt()
                .user(input)
                .call()
                .entity(ReferenceGoals.class);

        return referenceGoals.toString();
    }

    @GetMapping("/stream")
    Flux<String> generationStreaming(@RequestParam String goal) {

        return chatClient.prompt()
                .user(u -> u
                        .text("{goal}를 달성할 세부 할 일 5개 짜줘")
                        .param("goal", goal))
                .stream()
                .content();
    }
}
