package com.sajee.meetingmind.meeting.dto.response;

import com.sajee.meetingmind.meeting.enums.MeetingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record MeetingResponse(

        UUID uuid,

        String title,

        MeetingStatus status,

        LocalDateTime createdAt
) {
}
