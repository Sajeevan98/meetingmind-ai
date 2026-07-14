package com.sajee.meetingmind.analysis.dto;

import java.util.List;

public record MeetingAnalysisResult(
        String summary,
        List<ActionItem> actionItems,
        List<String> decisions
) {
}