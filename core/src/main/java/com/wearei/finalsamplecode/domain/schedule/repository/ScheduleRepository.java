package com.wearei.finalsamplecode.domain.schedule.repository;

import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByTeamIdAndId(Long teamId, Long scheduleId);

    @Query("SELECT s FROM Schedule s WHERE s.team.id = :teamId")
    List<Schedule> findSchedulesByTeamId(@Param("teamId") Long teamId);
}

