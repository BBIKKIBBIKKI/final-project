package com.wearei.finalsamplecode.core.domain.player.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.core.domain.team.entity.Team;
import com.wearei.finalsamplecode.core.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "players", indexes = {
        @Index(name = "idx_player_name_team_name", columnList = "player_name, team_name")
})
@AttributeOverride(name = "id", column = @Column(name = "player_id"))
public class Player extends BaseEntity {
    @Column(name = "player_age")
    private Long playerAge;

    @Column(name ="player_name")
    private String playerName;

    @Column(name = "team_name")
    private String teamName;

    private String position;

    @Column(name = "player_song")
    private String playerSong;

    @Column(name = "player_body_check")
    private String playerBodyCheck;

    private Long follow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false,  referencedColumnName="team_id")
    private Team team;

    public Player(Long playerId, Long playerAge, String playerName, String teamName, String position, String playerSong, String playerBodyCheck, Long follow) {
        this.id=playerId;
        this.playerAge=playerAge;
        this.playerName=playerName;
        this.teamName = teamName;
        this.position=position;
        this.playerSong=playerSong;
        this.playerBodyCheck=playerBodyCheck;
        this.follow=follow;
    }
}