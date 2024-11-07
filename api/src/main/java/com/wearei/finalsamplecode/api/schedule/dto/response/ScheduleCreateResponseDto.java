package com.wearei.finalsamplecode.api.schedule.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.wearei.finalsamplecode.domain.schedule.entity.Schedule;
import lombok.Getter;

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

    public ScheduleCreateResponseDto(Schedule schedule) {
        this.teamId = schedule.getTeam().getId();
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.ground = schedule.getGround();
        this.date = schedule.getDate();
        this.time = schedule.getTime();
        this.createAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}