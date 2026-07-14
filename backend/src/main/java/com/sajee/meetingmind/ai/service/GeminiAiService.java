package com.sajee.meetingmind.ai.service;

import com.sajee.meetingmind.ai.prompt.MeetingAnalysisPrompt;
import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiAiService implements AiService {

    private final ChatClient chatClient;

    public GeminiAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public MeetingAnalysisResult analyzeMeeting(String meetingContent) {

        String standardizedPrompt = MeetingAnalysisPrompt.build(meetingContent);

        return chatClient.prompt()
                .user(standardizedPrompt)
                .call()
                .entity(MeetingAnalysisResult.class);
    }
}
