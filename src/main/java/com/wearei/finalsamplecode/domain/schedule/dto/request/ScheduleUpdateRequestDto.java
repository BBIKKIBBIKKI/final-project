package com.wearei.finalsamplecode.domain.schedule.dto.request;

import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleUpdateRequestDto {
        private Long teamId;
        private Long id;
        private String title;
        private String contents;
        private String ground;
        private LocalDate date;
        private LocalTime time;
}
