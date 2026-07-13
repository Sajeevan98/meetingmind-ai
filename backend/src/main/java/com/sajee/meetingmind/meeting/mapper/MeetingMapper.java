package com.sajee.meetingmind.meeting.mapper;


import com.sajee.meetingmind.meeting.dto.response.MeetingResponse;
import com.sajee.meetingmind.meeting.entity.Meeting;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {

    public MeetingResponse toResponse(Meeting meeting) {

        return new MeetingResponse(
                meeting.getUuid(),
                meeting.getTitle(),
                meeting.getStatus(),
                meeting.getCreatedAt()
        );
    }
}
