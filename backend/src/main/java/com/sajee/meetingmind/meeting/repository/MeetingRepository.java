package com.sajee.meetingmind.meeting.repository;

import com.sajee.meetingmind.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Optional<Meeting> findByUuid(UUID uuid);
}
