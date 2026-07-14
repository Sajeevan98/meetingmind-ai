package com.sajee.meetingmind.ai.controller;

import com.sajee.meetingmind.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class TestController {

    private final AiService aiService;

    @GetMapping("/test")
    public String test() {
        return aiService.analyzeMeeting(
                "Explain Spring Boot in 3 sentences?"
        );
    }
}
