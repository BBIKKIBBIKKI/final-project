package com.wearei.finalsamplecode.domain.player.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@MappedSuperclass
@Table(name = "players")
@AttributeOverride(name = "id", column = @Column(name = "team_id"))
public class Player extends BaseEntity {

    @Column(name = "player_Id")
    private Long playerId;
    @Column(name = "team_Id")
    private Long teamId;
    @Column(name = "player_Age")
    private Long playerAge;
    @Column(name ="player_Name")
    private String playerName;
    @Column(name = "team_Name")
    private String teamName;
    private String position;
    @Column(name = "player_Song")
    private String playerSong;
    @Column(name = "player_BodyCheck")
    private String playerBodyCheck;
    private Long follow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    public Player(Long playerId, Long teamId, Long playerAge, String playerName, String teamName, String position, String playerSong, String playerBodyCheck, Long follow){
        this.playerId=playerId;
        this.teamId=teamId;
        this.playerAge=playerAge;
        this.playerName=playerName;
        this.position=position;
        this.playerSong=playerSong;
        this.playerBodyCheck=playerBodyCheck;
        this.follow=follow;
    }
}
