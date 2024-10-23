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
public class Schedule extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

//    @OneToOne
//    @JoinColumn(name = "chat_id")
//    private Chat chat;

    private String teamName;
    private String title;
    private String contents;
    private String ground;

    private LocalDate date;
    private LocalTime time;
}
