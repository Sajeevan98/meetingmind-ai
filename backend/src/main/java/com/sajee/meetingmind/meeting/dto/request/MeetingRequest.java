package com.sajee.meetingmind.meeting.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MeetingRequest(

        @NotBlank(message = "Title is required.")
        String title
) {
}
