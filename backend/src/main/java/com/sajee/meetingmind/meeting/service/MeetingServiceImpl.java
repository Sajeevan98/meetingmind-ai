package com.sajee.meetingmind.meeting.service;

import com.sajee.meetingmind.attachment.entity.MeetingAttachment;
import com.sajee.meetingmind.meeting.dto.request.MeetingRequest;
import com.sajee.meetingmind.meeting.dto.response.MeetingResponse;
import com.sajee.meetingmind.meeting.entity.Meeting;
import com.sajee.meetingmind.meeting.enums.MeetingStatus;
import com.sajee.meetingmind.meeting.mapper.MeetingMapper;
import com.sajee.meetingmind.meeting.repository.MeetingRepository;
import com.sajee.meetingmind.storage.config.FileStorageProperties;
import com.sajee.meetingmind.storage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties fileStorageProperties;

    @Override
    public MeetingResponse createMeeting(MeetingRequest request, MultipartFile file) {

        Meeting meeting = Meeting.builder()
                .uuid(UUID.randomUUID())
                .title(request.title())
                .status(MeetingStatus.UPLOADED)
                .build();

        String storedFilename = fileStorageService.storeFile(file);

        /*
                String filePath = fileStorageProperties.getUploadDir() + "/" + storedFilename;

                This is correct, But Windows uses: \
                Linux/macOS use: /
                Paths.get() automatically uses the correct separator for the operating system.
         */
        String filePath = Paths.get(fileStorageProperties.getUploadDir(), storedFilename).toString();

        MeetingAttachment meetingAttachment = MeetingAttachment.builder()
                .originalFileName(file.getOriginalFilename())
                .storedFileName(storedFilename)
                .filePath(filePath)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();

        meeting.addAttachment(meetingAttachment);

        Meeting savedMeeting = meetingRepository.save(meeting);

        return meetingMapper.toResponse(savedMeeting);
    }
}
