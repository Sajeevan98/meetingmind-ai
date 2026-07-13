package com.sajee.meetingmind.meeting.controller;

import com.sajee.meetingmind.common.ApiEndpoints;
import com.sajee.meetingmind.meeting.dto.request.MeetingRequest;
import com.sajee.meetingmind.meeting.dto.response.MeetingResponse;
import com.sajee.meetingmind.meeting.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.MEETINGS)
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MeetingResponse> create(
            @Valid @ModelAttribute MeetingRequest request,
            @RequestPart("file") MultipartFile file
    ) {

        MeetingResponse response = meetingService.createMeeting(request, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
