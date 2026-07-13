package com.sajee.meetingmind.meeting.service;

import com.sajee.meetingmind.meeting.dto.request.MeetingRequest;
import com.sajee.meetingmind.meeting.dto.response.MeetingResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MeetingService {

    MeetingResponse createMeeting(MeetingRequest request, MultipartFile file);
}
