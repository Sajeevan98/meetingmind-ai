package com.sajee.meetingmind.attachment.repository;

import com.sajee.meetingmind.attachment.entity.MeetingAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingAttachmentRepository extends JpaRepository<MeetingAttachment, Long> {

}
