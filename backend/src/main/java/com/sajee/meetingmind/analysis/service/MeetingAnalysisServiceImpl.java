package com.sajee.meetingmind.analysis.service;

import com.sajee.meetingmind.ai.dto.AiMeetingAnalysisResponse;
import com.sajee.meetingmind.ai.service.AiService;
import com.sajee.meetingmind.analysis.dto.MeetingAnalysisResponse;
import com.sajee.meetingmind.analysis.entity.MeetingAnalysis;
import com.sajee.meetingmind.analysis.mapper.MeetingAnalysisMapper;
import com.sajee.meetingmind.analysis.repository.MeetingAnalysisRepository;
import com.sajee.meetingmind.attachment.entity.MeetingAttachment;
import com.sajee.meetingmind.meeting.entity.Meeting;
import com.sajee.meetingmind.meeting.repository.MeetingRepository;
import com.sajee.meetingmind.document.service.DocumentExtractionService;
import com.sajee.meetingmind.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingAnalysisServiceImpl implements MeetingAnalysisService {

    private final MeetingRepository meetingRepository;
    private final MeetingAnalysisRepository meetingAnalysisRepository;
    private final DocumentExtractionService documentExtractionService;
    private final AiService aiService;
    private final MeetingAnalysisMapper meetingAnalysisMapper;

    @Override
    public MeetingAnalysisResponse analyzeMeeting(UUID meetingUuid) {

        Meeting meeting = meetingRepository.findByUuid(meetingUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with id: " + meetingUuid));

        // if one meeting --> one document
        // MeetingAttachment attachment = meeting.getAttachments().get(0);

        // if one meeting --> many documents
        MeetingAttachment attachment = meeting.getAttachments()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Meeting attachment not found."));

        // get text
        String meetingText = documentExtractionService.extract(
                Path.of(attachment.getFilePath()),
                attachment.getFileType()
        );

        // send text to ai-service
        AiMeetingAnalysisResponse result = aiService.analyzeMeeting(meetingText);

        // First analysis --> Version 1, Retry --> Version 2, Retry --> Version 3
        int version = meetingAnalysisRepository
                .findTopByMeetingOrderByAnalysisVersionDesc(meeting)
                .map(analysis -> analysis.getAnalysisVersion() + 1)
                .orElse(1);

        // create entity
        MeetingAnalysis analysis = MeetingAnalysis.builder()
                .uuid(UUID.randomUUID())
                .meeting(meeting)
                .summary(result.summary())
                .actionItems(result.actionItems())
                .decisions(result.decisions())
                .analysisVersion(version)
                .model("gemini-3.5-flash")
                .build();

        MeetingAnalysis savedAnalysis =
                meetingAnalysisRepository.save(analysis);

        return meetingAnalysisMapper.toResponse(savedAnalysis);
    }
}