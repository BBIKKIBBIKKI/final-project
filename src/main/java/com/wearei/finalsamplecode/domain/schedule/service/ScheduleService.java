package com.wearei.finalsamplecode.domain.schedule.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleCreateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public ScheduleCreateResponseDto createSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto, AuthUser authUser) {
        User user = userRepository.findById(authUser.getUserId()).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_USER));

        checkIfAdmin(user);
        //구단id확인
       Team team = findByTeamId(scheduleCreateRequestDto.getTeamId());

        // 일정 생성하기
        Schedule schedule = new Schedule(team,
                scheduleCreateRequestDto.getTitle(),
                scheduleCreateRequestDto.getContents(),
                scheduleCreateRequestDto.getGround(),
                scheduleCreateRequestDto.getDate(),
                scheduleCreateRequestDto.getTime()
        );
        //생성한 일정 저장
        Schedule createSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponseDto(scheduleCreateRequestDto.getTeamId(),
                createSchedule.getId(),
                createSchedule.getTitle(),
                createSchedule.getContents(),
                createSchedule.getGround(),
                createSchedule.getDate(),
                createSchedule.getTime(),
                createSchedule.getCreatedAt(),
                createSchedule.getModifiedAt()
        );
    }

    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        //구단 조회
       Team team = findByTeamId(scheduleUpdateRequestDto.getTeamId());
        //일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));
        //일정 수정
        schedule.updateSchedule(team,
                scheduleUpdateRequestDto.getTitle(),
                scheduleUpdateRequestDto.getContents(),
                scheduleUpdateRequestDto.getGround(),
                scheduleUpdateRequestDto.getDate(),
                scheduleUpdateRequestDto.getTime()
        );
        //일정 저장
        scheduleRepository.save(schedule);
        //일정 조회시 반환
        return new ScheduleUpdateResponseDto(scheduleUpdateRequestDto.getTeamId(),
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getGround(),
                schedule.getDate(),
                schedule.getTime(),
                schedule.getModifiedAt()
        );
    }

    public List<ScheduleSearchResponseDto> getSchedules(Long teamId) {
        findByTeamId(teamId);

        return scheduleRepository.findByTeam_Id(teamId).stream()
                .map(schedule -> new ScheduleSearchResponseDto(teamId,
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getGround(),
                        schedule.getDate(),
                        schedule.getTime(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()))
                .collect(Collectors.toList());
    }

    public ScheduleSearchResponseDto getSchedule(Long teamId, Long scheduleId) {
        findByTeamId(teamId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));

        //일정과 팀과의 연관관계 검증
        if(!schedule.getTeam().getId().equals(teamId)){
            throw new ApiException(ErrorStatus._FORBIDDEN);
        }

        return new ScheduleSearchResponseDto(teamId,
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getGround(),
                schedule.getDate(),
                schedule.getTime(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    public void deleteSchedule(Long scheduleId, Long teamId) {
        findByTeamId(teamId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));

        scheduleRepository.delete(schedule);
    }

    private Team findByTeamId (Long Id) {
        return teamRepository.findById(Id).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }

    public void checkIfAdmin(User user) {
        if (!user.getUserRole().equals((UserRole.ROLE_ADMIN))) {
            throw new ApiException(ErrorStatus._NOT_ADMIN_USER);
        }
    }
}
