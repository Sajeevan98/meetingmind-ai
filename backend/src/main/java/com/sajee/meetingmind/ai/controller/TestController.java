package com.sajee.meetingmind.ai.controller;

import com.sajee.meetingmind.ai.dto.AiMeetingAnalysisResponse;
import com.sajee.meetingmind.ai.service.AiService;
import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResponse;
import com.sajee.meetingmind.analysis.service.MeetingAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class TestController {

    private final AiService aiService;
    private final MeetingAnalysisService meetingAnalysisService;

    @GetMapping("/test")
    public AiMeetingAnalysisResponse test() {
        return aiService.analyzeMeeting(
                "Explain Spring Boot in 3 sentences?"
        );
    }

    @GetMapping("/test2/{uuid}")
    public MeetingAnalysisResponse test2(@PathVariable UUID uuid) {

        return meetingAnalysisService.analyzeMeeting(uuid);
    }

}
