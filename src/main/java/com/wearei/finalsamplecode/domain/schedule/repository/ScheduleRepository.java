package com.wearei.finalsamplecode.domain.schedule.repository;

import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTeamId(Long teamId);
}
