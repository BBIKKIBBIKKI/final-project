package com.wearei.finalsamplecode.core.domain.player.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "players", indexes = {
        @Index(name = "idx_player_name_team_name", columnList = "player_name, team_id")
})
@AttributeOverride(name = "id", column = @Column(name = "player_id"))
public class Player extends BaseEntity {
    @Column(name = "player_age")
    private Long playerAge;

    @Column(name = "player_name")
    private String playerName;

    private String position;

    @Column(name = "player_song")
    private String playerSong;

    @Column(name = "player_body_check")
    private String playerBodyCheck;

    private Long follow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(Long playerAge, String playerName, Team team, String position, String playerSong, String playerBodyCheck) {
        this.playerAge = playerAge;
        this.playerName = playerName;
        this.team = team;
        this.position = position;
        this.playerSong = playerSong;
        this.playerBodyCheck = playerBodyCheck;
    }

    public void incrementFollow() {
        this.follow++;
    }
}
