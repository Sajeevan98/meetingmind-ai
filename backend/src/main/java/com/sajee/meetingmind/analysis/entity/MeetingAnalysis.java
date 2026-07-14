package com.sajee.meetingmind.analysis.entity;

import com.sajee.meetingmind.common.AuditableEntity;
import com.sajee.meetingmind.meeting.entity.Meeting;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "meeting_analyses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MeetingAnalysis extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String actionItems;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String decisions;

    @Column(columnDefinition = "TEXT")
    private String rawResponse;

    @Column(length = 50, nullable = false)
    private String model; // gemini-2.5, gpt-4.1, gpt-5

    @Column(nullable = false)
    private Integer analysisVersion;
}
