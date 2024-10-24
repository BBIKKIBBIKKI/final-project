package com.wearei.finalsamplecode.domain.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
        private Long teamId;
        private Long id;
        private String title;
        private String contents;
        private String ground;
        private LocalDate date;
        private LocalTime time;
}