package com.article.article.model.response;

import com.article.article.model.dto.UserAccountDto;


public record UsernameResponse(
        String username
) {

    public static UsernameResponse of(String email) {
        return new UsernameResponse(email);
    }

    public static UsernameResponse from(UserAccountDto dto) {
        return new UsernameResponse(
                dto.username()
        );
    }

}
