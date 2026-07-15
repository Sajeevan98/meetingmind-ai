package com.sajee.meetingmind.analysis.mapper;

import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResponse;
import com.sajee.meetingmind.analysis.entity.MeetingAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingAnalysisMapper {

    public MeetingAnalysisResponse toResponse(MeetingAnalysis analysis) {

        return new MeetingAnalysisResponse(
                analysis.getUuid(),
                analysis.getSummary(),
                analysis.getActionItems(),
                analysis.getDecisions(),
                analysis.getAnalysisVersion(),
                analysis.getCreatedAt()
        );
    }
}
