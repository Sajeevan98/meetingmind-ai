package com.sajee.meetingmind.ai.service;

import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResult;

public interface AiService {

    MeetingAnalysisResult analyzeMeeting(String meetingContent);
}
