package com.wearei.finalsamplecode.domain.follow.entity;

import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="asfollows")
@AttributeOverride(name = "id", column = @Column(name = "team_id"))
public class Follow extends BaseEntity {
    @Column(name = "follow_Id")
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Follow(User user, Player player){
        this.user = user;
        this.player = player;
    }
}
