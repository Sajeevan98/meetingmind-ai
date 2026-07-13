package com.sajee.meetingmind.attachment.entity;

import com.sajee.meetingmind.meeting.entity.Meeting;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "meeting_attachments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MeetingAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_file_name", nullable = false, length = 255)
    private String originalFileName;

    @Column(name = "stored_file_name", nullable = false, length = 255)
    private String storedFileName;

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(name = "file_type", nullable = false, length = 255)
    private String fileType;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;
}
