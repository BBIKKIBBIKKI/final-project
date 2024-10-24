package com.wearei.finalsamplecode.domain.team.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.team.dto.request.TeamRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "team")
public class Team extends BaseEntity {
    @Column(name="team_name", length=50)
    private String teamName;
    @Column(name="uniform_img")
    private String uniformImg;
    @Column(name="mascot_img")
    private String mascotImg;
    @Column(name="equipment_img")
    private String equipmentImg;
    @Column(name="theme_song")
    private String themeSong;

    public Team(TeamRequest teamRequest) {
        this.teamName = teamRequest.getTeamName();
        this.uniformImg = teamRequest.getUniformImg();
        this.mascotImg = teamRequest.getMascotImg();
        this.equipmentImg = teamRequest.getEquipmentImg();
        this.themeSong = teamRequest.getThemeSong();
    }
}