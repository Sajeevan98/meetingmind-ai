package com.sajee.meetingmind.ai.service;

import com.sajee.meetingmind.ai.dto.AiMeetingAnalysisResponse;

public interface AiService {

    AiMeetingAnalysisResponse analyzeMeeting(String meetingContent);
}
