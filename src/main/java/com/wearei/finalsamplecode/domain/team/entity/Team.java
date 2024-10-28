package com.wearei.finalsamplecode.domain.team.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "team_id"))
@Table(name = "teams")
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

    public Team(String teamName, String uniformImg, String mascotImg, String equipmentImg, String themeSong) {
        this.teamName = teamName;
        this.uniformImg = uniformImg;
        this.mascotImg = mascotImg;
        this.equipmentImg = equipmentImg;
        this.themeSong = themeSong;
    }
}