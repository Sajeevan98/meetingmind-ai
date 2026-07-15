package com.sajee.meetingmind.analysis.repository;

import com.sajee.meetingmind.analysis.entity.MeetingAnalysis;
import com.sajee.meetingmind.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingAnalysisRepository extends JpaRepository<MeetingAnalysis, Long> {

    Optional<MeetingAnalysis> findTopByMeetingOrderByAnalysisVersionDesc(Meeting meeting);
}
