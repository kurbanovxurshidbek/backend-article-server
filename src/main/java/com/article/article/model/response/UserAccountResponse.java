package com.article.article.model.response;

import com.article.article.model.dto.UserAccountDto;

public record UserAccountResponse(
        Long id,
        String username,
        String nickname
) {

    public static UserAccountResponse of(Long id, String username, String nickname) {
        return new UserAccountResponse(id, username, nickname);
    }

    public static UserAccountResponse from(UserAccountDto dto) {
        return new UserAccountResponse(
                dto.id(),
                dto.username(),
                dto.nickname()
        );
    }

}