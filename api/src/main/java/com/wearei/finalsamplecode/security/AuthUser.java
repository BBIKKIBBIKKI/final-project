package com.wearei.finalsamplecode.security;

import com.wearei.finalsamplecode.common.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long userId;
    private final String email;
    private final UserRole userRole;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long userId, String email, UserRole userRole) {
        this.userId = userId;
        this.email = email;
        this.userRole = userRole;
        this.authorities= List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
