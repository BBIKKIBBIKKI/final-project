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
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping()
    public ApiResponse<ScheduleCreateResponseDto> createSchedule(@AuthenticationPrincipal AuthUser authUser, @RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        return ApiResponse.onSuccess(scheduleService.createSchedule(scheduleCreateRequestDto, authUser));
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        return ApiResponse.onSuccess(scheduleService.updateSchedule(scheduleId, scheduleUpdateRequestDto));
    }

    @GetMapping()
    public ApiResponse<List<ScheduleSearchResponseDto>> getSchedules(@RequestParam Long teamId) {
        return ApiResponse.onSuccess(scheduleService.getSchedules(teamId));
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleSearchResponseDto> getSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(scheduleService.getSchedule(teamId, scheduleId));
    }

    @DeleteMapping("/{scheduleId}")
    public ApiResponse<String> deleteSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        scheduleService.deleteSchedule(scheduleId, teamId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}