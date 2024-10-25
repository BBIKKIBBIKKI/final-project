package com.wearei.finalsamplecode.domain.schedule.repository;

import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTeam_Id(Long teamId);
    ScheduleSearchResponseDto findByTeamIdAndSchedulId(Long teamId, Long scheduleId);
}

