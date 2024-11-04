package com.wearei.finalsamplecode.api.schedule.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
        private Long teamId;
        private String title;
        private String contents;
        private String ground;
        private LocalDate date;
        private LocalTime time;
}