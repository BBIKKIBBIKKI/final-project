package com.wearei.finalsamplecode.domain.schedule.repository;

import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTeam_Id(Long teamId);
    Schedule findByTeamIdAndId(Long teamId, Long scheduleId);
}

