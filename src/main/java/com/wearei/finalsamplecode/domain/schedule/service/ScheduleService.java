package com.wearei.finalsamplecode.domain.schedule.service;

import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
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
                createSchedule.getCreatedAt()
        );
    }

    public ScheduleUpdateResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto){
        //구단 ID확인
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
        scheduleRepository.save(schedule);

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





    private Team findByTeamId (Long teamId){
        return teamRepository.findById(teamId).orElseThrow(() -> new ApiException(ErrorStatus._NOT_FOUND_TEAM));
    }
}
