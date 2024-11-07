package com.wearei.finalsamplecode.domain.schedule.entity;

import com.wearei.finalsamplecode.domain.team.entity.Team;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        this.date = date;
        this.time = time;
    }

    public void updateSchedule(Team team, String title, String contents, String ground, LocalDate date, LocalTime time) {
        this.team = team;
        this.title = title;
        this.contents = contents;
        this.ground = ground;
        this.date = date;
        this.time = time;
    }

    public void updateContents(String contents) {
            this.contents = contents;
    }

    public void updateTitle(String title) {
            this.title = title;
    }

    public void updateGround(String ground) {
            this.ground = ground;
    }

    public void updateDate(LocalDate date) {
            this.date = date;
    }

    public void updateTime(LocalTime time) {
            this.time = time;
    }
}