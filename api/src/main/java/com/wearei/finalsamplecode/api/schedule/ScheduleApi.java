package com.wearei.finalsamplecode.api.schedule;

import com.wearei.finalsamplecode.api.schedule.dto.request.ScheduleCreateRequestDto;
import com.wearei.finalsamplecode.api.schedule.dto.request.ScheduleUpdateRequestDto;
import com.wearei.finalsamplecode.api.schedule.dto.response.ScheduleCreateResponseDto;
import com.wearei.finalsamplecode.api.schedule.dto.response.ScheduleSearchResponseDto;
import com.wearei.finalsamplecode.api.schedule.dto.response.ScheduleUpdateResponseDto;
import com.wearei.finalsamplecode.apipayload.ApiResponse;
import com.wearei.finalsamplecode.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleApi {
    private final ScheduleService scheduleService;
    private final DefaultScheduleService defaultScheduleService;

    @PostMapping
    public ApiResponse<ScheduleCreateResponseDto> createSchedule(@AuthenticationPrincipal AuthUser authUser, @RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        return ApiResponse.onSuccess(new ScheduleCreateResponseDto(scheduleService.createSchedule(scheduleCreateRequestDto.getTeamId(), scheduleCreateRequestDto.getTitle(), scheduleCreateRequestDto.getContents(), scheduleCreateRequestDto.getGround(), scheduleCreateRequestDto.getDate(), scheduleCreateRequestDto.getTime(), authUser)));
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto request) {
        return ApiResponse.onSuccess(new ScheduleUpdateResponseDto(scheduleService.updateSchedule(scheduleId, request.getTeamId(), request.getTitle(), request.getContents(), request.getGround(), request.getDate(), request.getTime())));
    }

    @GetMapping
    public ApiResponse<List<ScheduleSearchResponseDto>> getSchedules(@RequestBody Long teamId) {
        List<Schedule> schedules = defaultScheduleService.getSchedules(teamId);
        List<ScheduleSearchResponseDto> responses = schedules.stream()
                .map(ScheduleSearchResponseDto::new).toList();

        return ApiResponse.onSuccess(responses);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleSearchResponseDto> getSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(new ScheduleSearchResponseDto(defaultScheduleService.getSchedule(teamId, scheduleId)));
    }

    @DeleteMapping("/{scheduleId}")
    public ApiResponse<String> deleteSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        scheduleService.deleteSchedule(scheduleId, teamId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}