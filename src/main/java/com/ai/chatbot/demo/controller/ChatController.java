package com.ai.chatbot.demo.controller;

import com.ai.chatbot.demo.dto.response.ReferenceGoals;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @GetMapping("/ai")
    String generation(@RequestParam("input") String input) {
        ReferenceGoals referenceGoals = chatClient.prompt()
                .user(input)
                .call()
                .entity(ReferenceGoals.class);

        return referenceGoals.toString();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> generationStreaming(
            @RequestParam("goal") String goal,
            @RequestParam("conversationId") String conversationId
    ) {

        return chatClient.prompt()
                .user(u -> u
                        .text("{goal}를 달성할 세부 할 일 5개 짜줘")
                        .param("goal", goal))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content();
    }
}
