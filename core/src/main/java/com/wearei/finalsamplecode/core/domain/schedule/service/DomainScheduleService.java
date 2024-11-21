package com.wearei.finalsamplecode.core.domain.schedule.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.common.support.Preconditions;
import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.core.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public Schedule createSchedule(Long userId, Long teamId, String title, String contents, String ground, LocalDate date, LocalTime time) {

        User user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(!user.isNotSameRole(UserRole.ROLE_ADMIN), ErrorStatus._NOT_OWNER_USER);

        Team team = teamRepository.findByIdOrThrow(teamId);

        return scheduleRepository.save(new Schedule(
                team,
                title,
                contents,
                ground,
                date,
                time
        ));
    }

    public Schedule updateSchedule(Long userId, Long scheduleId, Long teamId) {

        User user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(!user.isNotSameRole(UserRole.ROLE_ADMIN), ErrorStatus._NOT_OWNER_USER);

        //구단 조회
        Team team = teamRepository.findByIdOrThrow(teamId);

        //일정 확인
        Schedule schedule = scheduleRepository.findByIdOrThrow(scheduleId);

        //일정 수정
        schedule.updateSchedule(
                team,
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getGround(),
                schedule.getDate(),
                schedule.getTime()
        );

        //일정 조회시 반환
        return schedule;
    }

    public void deleteSchedule(Long userId, Long scheduleId, Long teamId) {

        User user = userRepository.findByIdOrThrow(userId);

        Preconditions.validate(!user.isNotSameRole(UserRole.ROLE_ADMIN), ErrorStatus._NOT_OWNER_USER);

        teamRepository.findByIdOrThrow(teamId);

        Schedule schedule = scheduleRepository.findByIdOrThrow(scheduleId);

        scheduleRepository.delete(schedule);
    }
}
