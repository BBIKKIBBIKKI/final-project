package com.wearei.finalsamplecode.domain.ground.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ground_id"))
@Table(name = "grounds")
public class Ground extends BaseEntity {
    @Column(name = "ground_name", length = 50)
    private String groundName;

    @Column(length = 50)
    private String location;

    @Column(length = 15)
    private String tel;

    @Column(name="ground_img")
    private String groundImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Ground(String groundName, String location, String tel, String groundImg, Team team) {
        this.groundName = groundName;
        this.location = location;
        this.tel = tel;
        this.groundImg = groundImg;
        this.team = team;
    }
}
