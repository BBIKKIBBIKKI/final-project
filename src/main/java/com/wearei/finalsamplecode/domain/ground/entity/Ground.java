package com.wearei.finalsamplecode.domain.ground.entity;

import com.wearei.finalsamplecode.common.entity.BaseEntity;
import com.wearei.finalsamplecode.domain.ground.dto.request.GroundRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ground_id"))
@Table(name = "ground")
public class Ground extends BaseEntity {
    @Column(name = "ground_name", length = 50)
    private String groundName;
    @Column(length = 50)
    private String location;
    @Column(length = 15)
    private String tel;

    public Ground(GroundRequest groundRequest) {
        this.groundName = groundRequest.getGroundName();
        this.location = groundRequest.getLocation();
        this.tel = groundRequest.getTel();
    }
}
