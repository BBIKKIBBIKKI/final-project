package com.wearei.finalsamplecode.domain.user.entity;

import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.domain.comment.entity.Comment;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends Timestamped {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isDeleted = false;

    public User(String email,String username, String Password, UserRole userRole) {
        this.email = email;
        this.username = username;
        this.password = Password;
        this.userRole = userRole;
    }

    // 사용자를 삭제 상태로 변경하는 메서드
    public void markIsDeleted(){
        this.isDeleted = true;
    }

    public void updateUser(String name, String email, String password) {
        this.username=name;
        this.email=email;
        this.password=password;
    }

    public User(Long id) {
        this.id = id;
    }

    public static User fromAuthUser(AuthUser authUser) {

        return new User(authUser.getUserId());
    }
}
