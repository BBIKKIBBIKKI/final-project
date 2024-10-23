package com.wearei.finalsamplecode.domain.schedule.dto.request;

import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleRequestDto {
    private Long teamId;
    private String title;
    private String contents;
    private String ground;
    private LocalDate date;
    private LocalTime time;
}
