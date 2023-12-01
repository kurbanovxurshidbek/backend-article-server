package com.article.article.model.security;

import com.article.article.model.entity.RoleType;
import com.article.article.model.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

// #principle - 1
public record UserPrinciple(
        String username,
        String password,
        String nickname,
        Set<RoleType> roles
) implements UserDetails {


    public static UserPrinciple of(String username, String password, String nickname, Set<RoleType> roles) {
        return new UserPrinciple(
                username,
                password,
                nickname,
                roles

        );
    }

    public static UserPrinciple from(UserAccount userAccount) {
        return UserPrinciple.of(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getNickname(),
                userAccount.getRoles()
        );
    }

    public UserAccount toEntity() {
        UserAccount userAccount = UserAccount.of(username, password, nickname);
        userAccount.setRoles(roles);
        return userAccount;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleType role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}