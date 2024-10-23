package com.wearei.finalsamplecode.domain.schedule.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.schedule.service.ScheduleService;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.domain.team.service.TeamService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private ScheduleRepository scheduleRepository;
    private ScheduleService scheduleService;
    private TeamService teamService;

    @PostMapping()
    public ApiResponse<ScheduleResponseDto> createSchedule(@RequestBody Team team, ScheduleRequestDto scheduleRequestDto){

        ScheduleResponseDto createSchedule = scheduleService.createSchedule(team, scheduleRequestDto);

        return ApiResponse.onSuccess(createSchedule);
    }

    @PatchMapping()
    public ApiResponse<ScheduleUpdateResponseDto> updateSchedule(@RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto){

        ScheduleUpdateResponseDto updateSchedule = scheduleService.updateSchedule(scheduleUpdateRequestDto);

        return ApiResponse.onSuccess(updateSchedule);
    }

    @GetMapping()
    public ApiResponse<List<ScheduleResponseDto>> getSchedules(@PathVariable Long teamId){

        List<ScheduleResponseDto> schedules = scheduleService.getSchedules(teamId);

        return ApiResponse.onSuccess(schedules);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleResponseDto> getSchedule(@PathVariable Long teamId, Long scheduleId){

        ScheduleResponseDto schedule = scheduleService.getSchedule(teamId, scheduleId);

        return ApiResponse.onSuccess(schedule);
    }

    @DeleteMapping("/scheduleId")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long teamId, Long scheduleId){

        scheduleService.deleteSchedule(teamId,scheduleId);

     return ApiResponse.onSuccess(null);
    }
}
