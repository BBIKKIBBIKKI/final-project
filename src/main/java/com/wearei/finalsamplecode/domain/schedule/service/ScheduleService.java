package com.wearei.finalsamplecode.domain.schedule.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.repository.TeamRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;

    public ScheduleResponseDto createSchedule(Team team, ScheduleRequestDto scheduleRequestDto) {
        //구단id확인
        findByTeamId(scheduleRequestDto.getTeamId());
        // 일정 생성하기
        Schedule schedule = new Schedule(team,
                scheduleRequestDto.getTitle(),
                scheduleRequestDto.getContents(),
                scheduleRequestDto.getGround(),
                scheduleRequestDto.getDate(),
                scheduleRequestDto.getTime()
        );
        //생성한 일정 저장
        Schedule createSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(scheduleRequestDto.getTeamId(),
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

    public ScheduleUpdateResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto){
        //구단 조회
        findByTeamId(scheduleUpdateRequestDto.getTeamId());
        //일정 확인
        Schedule schedule = scheduleRepository.findById(scheduleUpdateRequestDto.getId()).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));
        //일정 수정
        schedule.updateSchedule(scheduleUpdateRequestDto.getTitle(),
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

    public List<ScheduleResponseDto> getSchedules(Long teamId){

        findByTeamId(teamId);

        return scheduleRepository.findByTeamId(teamId).stream()
                .map(schedule -> new ScheduleResponseDto(teamId,
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

    public ScheduleResponseDto getSchedule(Long teamId,Long scheduleId){
        Team team = findByTeamId(teamId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));
        //일정과 팀과의 연관관계 검증
        if(!schedule.getTeam().getTeamId().equals(team.getTeamId())){
            throw new ApiException(ErrorStatus._FORBIDDEN);
        }

        return new ScheduleResponseDto(teamId,
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getGround(),
                schedule.getDate(),
                schedule.getTime(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    public void deleteSchedule(Long teamId, Long scheduleId){
        Team team = findByTeamId(teamId);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_SCHEDULE));
        //일정과 팀과의 연관관계 검증
        if(schedule.getTeam() == null || !schedule.getTeam().getTeamId().equals(team.getTeamId())) {
            throw new ApiException(ErrorStatus._FORBIDDEN);
        }
        scheduleRepository.delete(schedule);
    }

    private Team findByTeamId (Long teamId){
        return teamRepository.findById(teamId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}
