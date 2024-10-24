package com.wearei.finalsamplecode.domain.schedule.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleCreateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.repository.ScheduleRepository;
import com.wearei.finalsamplecode.domain.schedule.service.ScheduleService;
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
    public ApiResponse<ScheduleCreateResponseDto> createSchedule( @RequestBody ScheduleCreateRequestDto scheduleRequestDto){

        ScheduleCreateResponseDto createSchedule = scheduleService.createSchedule(scheduleRequestDto);

        return ApiResponse.onSuccess(createSchedule);
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleUpdateResponseDto> updateSchedule(@RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto){

        ScheduleUpdateResponseDto updateSchedule = scheduleService.updateSchedule(scheduleUpdateRequestDto);

        return ApiResponse.onSuccess(updateSchedule);
    }

    @GetMapping()
    public ApiResponse<List<ScheduleSearchResponseDto>> getSchedules(@RequestBody Long teamId){

        List<ScheduleSearchResponseDto> schedules = scheduleService.getSchedules(teamId);

        return ApiResponse.onSuccess(schedules);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleSearchResponseDto> getSchedule(@PathVariable Long scheduleId, @RequestBody Long teamId){

        ScheduleSearchResponseDto schedule = scheduleService.getSchedule(teamId, scheduleId);

        return ApiResponse.onSuccess(schedule);
    }

    @DeleteMapping("/scheduleId")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestBody Long teamId){

        scheduleService.deleteSchedule(teamId,scheduleId);

     return ApiResponse.onSuccess(null);
    }
}