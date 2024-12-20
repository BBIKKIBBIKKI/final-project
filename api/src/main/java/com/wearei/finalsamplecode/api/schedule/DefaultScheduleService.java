package com.wearei.finalsamplecode.api.schedule;

import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.core.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getTeamAllSchedules(Long teamId) {
        return scheduleRepository.findSchedulesByTeamId(teamId);
    }

    public Schedule getTeamSchedule(Long teamId, Long scheduleId) {
        return scheduleRepository.findByTeamIdAndId(teamId, scheduleId);
    }
}
