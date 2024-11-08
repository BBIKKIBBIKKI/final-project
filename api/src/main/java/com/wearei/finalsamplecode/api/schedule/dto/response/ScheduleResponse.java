package com.wearei.finalsamplecode.api.schedule.dto.response;

import com.wearei.finalsamplecode.core.domain.schedule.entity.Schedule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static com.wearei.finalsamplecode.api.schedule.dto.response.ScheduleResponse.*;

public sealed interface ScheduleResponse permits Create, Update, GetAll, Get {

    record Create (
            Long teamId,
            Long id,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time,
            LocalDateTime createAt,
            LocalDateTime modifiedAt
    ) implements ScheduleResponse {
        public Create (Schedule schedule){
            this (
                    schedule.getTeam().getId(),
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getGround(),
                    schedule.getDate(),
                    schedule.getTime(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
    }

    record Update (
            Long teamId,
            Long id,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time,
            LocalDateTime createAt,
            LocalDateTime modifiedAt
    ) implements ScheduleResponse {
        public Update (Schedule schedule){
            this (
                    schedule.getTeam().getId(),
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getGround(),
                    schedule.getDate(),
                    schedule.getTime(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
    }

    record GetAll (
            Long teamId,
            Long id,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time,
            LocalDateTime createAt,
            LocalDateTime modifiedAt
    ) implements ScheduleResponse {
        public GetAll (Schedule schedule){
            this (
                    schedule.getTeam().getId(),
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getGround(),
                    schedule.getDate(),
                    schedule.getTime(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
    }

    record Get (
            Long teamId,
            Long id,
            String title,
            String contents,
            String ground,
            LocalDate date,
            LocalTime time,
            LocalDateTime createAt,
            LocalDateTime modifiedAt
    ) implements ScheduleResponse {
        public Get (Schedule schedule){
            this (
                    schedule.getTeam().getId(),
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getGround(),
                    schedule.getDate(),
                    schedule.getTime(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
    }
}
