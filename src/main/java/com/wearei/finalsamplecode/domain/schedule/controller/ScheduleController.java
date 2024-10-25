package com.wearei.finalsamplecode.domain.schedule.controller;

import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleCreateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.domain.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.domain.schedule.service.ScheduleService;
import com.wearei.finalsamplecode.domain.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final TeamService teamService;

    @PostMapping()
    public ApiResponse<ScheduleCreateResponseDto> createSchedule(@AuthenticationPrincipal AuthUser authUser, @RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        ScheduleCreateResponseDto createSchedule = scheduleService.createSchedule(scheduleCreateRequestDto, authUser);
        return ApiResponse.onSuccess(createSchedule);
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        ScheduleUpdateResponseDto updateSchedule = scheduleService.updateSchedule(scheduleId, scheduleUpdateRequestDto);
        return ApiResponse.onSuccess(updateSchedule);
    }

    @GetMapping()
    public ApiResponse<List<ScheduleSearchResponseDto>> getSchedules(@RequestBody Long teamId) {
        List<ScheduleSearchResponseDto> schedules = scheduleService.getSchedules(teamId);
        return ApiResponse.onSuccess(schedules);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleSearchResponseDto> getSchedule(@PathVariable Long scheduleId, @RequestBody Long teamId) {
        ScheduleSearchResponseDto schedule = scheduleService.getSchedule(teamId, scheduleId);
        return ApiResponse.onSuccess(schedule);
    }

    @DeleteMapping("/scheduleId")
    public ApiResponse<String> deleteSchedule(@PathVariable Long scheduleId, @RequestBody Long teamId) {
        scheduleService.deleteSchedule(teamId, scheduleId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());

    }
}