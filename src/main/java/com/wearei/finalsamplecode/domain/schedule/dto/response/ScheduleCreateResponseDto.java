package com.wearei.finalsamplecode.domain.schedule.dto.response;

import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class ScheduleCreateResponseDto {
    private final Long teamId;
    private final Long id;
    private final String title;
    private final String contents;
    private final String ground;
    private final LocalDate date;
    private final LocalTime time;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public ScheduleCreateResponseDto (Long teamId, Long id, String title, String contents, String ground, LocalDate date, LocalTime time, LocalDateTime createAt, LocalDateTime modifiedAt){
        this.teamId = teamId;
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.ground = ground;
        this.date = date;
        this.time = time;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}