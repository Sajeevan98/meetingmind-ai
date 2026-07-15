package com.sajee.meetingmind.analysis.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MeetingAnalysisResponse(

        UUID uuid,

        String summary,

        List<ActionItem> actionItems,

        List<String> decisions,

        Integer analysisVersion,

        LocalDateTime createdAt
) {
}

// REST API output