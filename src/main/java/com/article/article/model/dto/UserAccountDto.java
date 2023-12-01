package com.article.article.model.dto;

import com.article.article.model.entity.UserAccount;

public record UserAccountDto(
        Long id,
        String username,
        String password,
        String nickname
) {
    public static UserAccountDto of(Long id, String username, String password, String nickname) {
        return new UserAccountDto(id, username, password, nickname);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getNickname()
        );
    }
}
