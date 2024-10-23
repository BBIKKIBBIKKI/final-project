package com.wearei.finalsamplecode.domain.user.entity;

import com.wearei.finalsamplecode.common.dto.AuthUser;
import com.wearei.finalsamplecode.common.entity.Timestamped;
import com.wearei.finalsamplecode.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserRole userRole;

    public User(String email, String Password, UserRole userRole) {
        this.email = email;
        this.password = Password;
        this.userRole = userRole;
    }

    public User(Long id) {
        this.id = id;
    }

    public static User fromAuthUser(AuthUser authUser) {

        return new User(authUser.getId());
    }
}