package com.wearei.finalsamplecode.api.schedule.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequestDto {
    private Long teamId;
    private String title;
    private String contents;
    private String ground;
    private LocalDate date;
    private LocalTime time;
}