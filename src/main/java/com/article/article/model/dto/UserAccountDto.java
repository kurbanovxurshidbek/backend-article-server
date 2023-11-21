package com.article.article.model.dto;

import com.article.article.model.entity.UserAccount;

public record UserAccountDto(
        String userId,
        String password,
        String email,
        String nickname,
        String memo
) {
    public static UserAccountDto of(String userId, String password, String email, String nickname, String memo) {
        return new UserAccountDto(userId, password, email, nickname, memo);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo()
        );
    }
}
