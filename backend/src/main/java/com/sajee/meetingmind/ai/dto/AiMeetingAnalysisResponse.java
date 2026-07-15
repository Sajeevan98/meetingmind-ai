package com.sajee.meetingmind.ai.dto;


import com.sajee.meetingmind.analysis.dto.ActionItem;

import java.util.List;

public record AiMeetingAnalysisResponse(
        String summary,
        List<ActionItem> actionItems,
        List<String> decisions
) {
}

// AI output