package com.sajee.meetingmind.analysis.service;

import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResponse;

import java.util.UUID;

public interface MeetingAnalysisService {

    MeetingAnalysisResponse analyzeMeeting(UUID meetingUuid);
}
