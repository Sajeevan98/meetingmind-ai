package com.sajee.meetingmind.meeting.entity;

import com.sajee.meetingmind.analysis.entity.MeetingAnalysis;
import com.sajee.meetingmind.attachment.entity.MeetingAttachment;
import com.sajee.meetingmind.common.AuditableEntity;
import com.sajee.meetingmind.meeting.enums.MeetingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meetings")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Meeting extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false, length = 255)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private MeetingStatus status;

    @Builder.Default
    @OneToMany(
            mappedBy = "meeting",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<MeetingAttachment> attachments = new ArrayList<>();

    //   Maintain both sides of the bidirectional relationship.
    public void addAttachment(MeetingAttachment attachment) {
        attachments.add(attachment);
        attachment.setMeeting(this);
    }

    public void removeAttachment(MeetingAttachment attachment) {
        attachments.remove(attachment);
        attachment.setMeeting(null);
    }

    @Builder.Default
    @OneToMany(
            mappedBy = "meeting",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MeetingAnalysis> analyses = new ArrayList<>();

    // helper methods for maintain both sides...
    public void addAnalysis(MeetingAnalysis analysis) {
        analyses.add(analysis);
        analysis.setMeeting(this);
    }

    public void removeAnalysis(MeetingAnalysis analysis) {
        analyses.remove(analysis);
        analysis.setMeeting(null);
    }
}
