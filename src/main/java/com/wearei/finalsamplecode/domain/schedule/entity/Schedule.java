package com.wearei.finalsamplecode.domain.schedule.entity;

import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedules")
@AttributeOverride(name = "id", column = @Column(name = "schedule_id"))
public class Schedule extends Timestamped {
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String title;

    private String contents;

    private String ground;

    private LocalDate date;

    private LocalTime time;

    public Schedule(Team team, String title, String contents, String ground, LocalDate date, LocalTime time) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.ground = ground;
        this.time = time;
        this.date = date;
    }

    public void updateSchedule(Team team, String title, String contents, String ground, LocalDate date, LocalTime time) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.ground = ground;
        this.date = date;
        this.time = time;
    }

    public void setContents(String contents) {
        if (contents != null) {
            this.contents = contents;
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    public void setGround(String ground) {
        if (ground != null) {
            this.ground = ground;
        }
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        }
    }

    public void setTime(LocalTime time) {
        if (time != null) {
            this.time = time;
        }
    }
}