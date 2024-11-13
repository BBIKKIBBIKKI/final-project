package com.wearei.finalsamplecode.api.schedule;

import com.wearei.finalsamplecode.api.schedule.dto.request.ScheduleRequest;
import com.wearei.finalsamplecode.api.schedule.dto.response.ScheduleResponse;
import com.wearei.finalsamplecode.common.ApiResponse;
import com.wearei.finalsamplecode.common.apipayload.status.SuccessStatus;
import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import com.wearei.finalsamplecode.core.domain.schedule.service.DomainScheduleService;
import com.wearei.finalsamplecode.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleApi {
    private final DomainScheduleService domainScheduleService;
    private final DefaultScheduleService defaultScheduleService;

    @PostMapping
    public ApiResponse<ScheduleResponse.Create> createSchedule(@AuthenticationPrincipal AuthUser authUser, @RequestBody ScheduleRequest.Create request) {
        return ApiResponse.onSuccess(new ScheduleResponse.Create(domainScheduleService.createSchedule(authUser.getUserId(), request.teamId(), request.title(), request.contents(), request.ground(), request.date(), request.time())));
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleResponse.Update> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequest.Update request) {
        return ApiResponse.onSuccess(new ScheduleResponse.Update(domainScheduleService.updateSchedule(scheduleId, request.teamId())));
    }

    @GetMapping
    public ApiResponse<List<ScheduleResponse.GetAll>> getSchedules(@RequestBody Long teamId) {
        List<Schedule> schedules = defaultScheduleService.getSchedules(teamId);
        List<ScheduleResponse.GetAll> responses = schedules.stream()
                .map(ScheduleResponse.GetAll::new).toList();
        return ApiResponse.onSuccess(responses);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleResponse.Get> getSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        return ApiResponse.onSuccess(new ScheduleResponse.Get(defaultScheduleService.getSchedule(teamId, scheduleId)));
    }

    @DeleteMapping("/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestParam Long teamId) {
        domainScheduleService.deleteSchedule(scheduleId, teamId);
        return ApiResponse.onSuccess(SuccessStatus._DELETION_SUCCESS.getMessage());
    }
}