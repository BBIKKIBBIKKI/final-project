package com.wearei.finalsamplecode.domain.schedule.repository;

import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
