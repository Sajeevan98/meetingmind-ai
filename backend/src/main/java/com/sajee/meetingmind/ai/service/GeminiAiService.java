package com.sajee.meetingmind.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiAiService implements AiService{

    private final ChatClient chatClient;

    @Override
    public String analyzeMeeting(String meetingContent) {
        return "";
    }
}
