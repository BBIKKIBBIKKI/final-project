package com.wearei.finalsamplecode.core.domain.schedule.service;

import com.wearei.finalsamplecode.common.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.core.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import com.wearei.finalsamplecode.common.enums.UserRole;
import com.wearei.finalsamplecode.core.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public Schedule createSchedule(Long userId, Long teamId, String title, String contents, String ground, LocalDate date, LocalTime time) {
        User user = userRepository.findByIdOrThrow(userId);

        if(user.isNotSameRole(UserRole.ROLE_ADMIN)) {
            throw new ApiException(ErrorStatus._NOT_OWNER_USER);
        }

        Team team = teamRepository.findByTeamId(teamId);

        return scheduleRepository.save(new Schedule(
                team,
                title,
                contents,
                ground,
                date,
                time
        ));
    }

    public Schedule updateSchedule(Long scheduleId, Long teamId, String title, String contents, String ground, LocalDate date, LocalTime time) {
        //구단 조회
       Team team = teamRepository.findByTeamId(teamId);
        //일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));

        if(Objects.nonNull(title)){
            schedule.updateTitle(title);
        }

        if(Objects.nonNull(contents)){
            schedule.updateContents(contents);
        }

        if(Objects.nonNull(ground)){
            schedule.updateGround(ground);
        }

        if(Objects.nonNull(date)){
            schedule.updateDate(date);
        }

        if(Objects.nonNull(time)){
            schedule.updateTime(time);
        }
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

    public void deleteSchedule(Long scheduleId, Long teamId) {
        teamRepository.findByTeamId(teamId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));

        scheduleRepository.delete(schedule);
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals((UserRole.ROLE_ADMIN))) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }
}
