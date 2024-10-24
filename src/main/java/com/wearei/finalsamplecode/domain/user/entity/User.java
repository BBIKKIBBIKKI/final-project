package com.wearei.finalsamplecode.domain.user.entity;

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

//    @AttributeOverride(name="id", column = @Column(name = "team_id"))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isDeleted = false;

    public User(String email,String username, String Password, UserRole userRole) {
        this.email = email;
        this.username = username;
        this.password = Password;
        this.userRole = userRole;
    }
//
//    // 비밀번호를 변경하는 메서드
//    public void changePassword(String password) {
//        this.password = password;
//    }

    // 사용자를 삭제 상태로 변경하는 메서드
    public void markIsDeleted(){
        this.isDeleted = true;
    }

    public void updateUser(String name, String email, String password){
        this.username=name;
        this.email=email;
        this.password=password;
    }
}
