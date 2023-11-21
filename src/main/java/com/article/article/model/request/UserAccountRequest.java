package com.article.article.model.request;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public record UserAccountRequest(
        @NotBlank(message = "userId is required")
        @Size(min = 5, max = 20)
        String userId,

        @NotBlank(message = "password is required")
        @Size(min = 5, max = 20)
        String password,

        @NotBlank(message = "email is required")
        @Email
        String email,

        @NotBlank(message = "nickname is required")
        @Size(min = 5, max = 20)
        String nickname,

        @NotBlank(message = "memo is required")
        @Size(min = 5, max = 100)
        String memo
) {

    public static UserAccountRequest of(String userId, String password, String email, String nickname, String memo) {
        return new UserAccountRequest(userId, password, email, nickname, memo);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(userId, password, email, nickname, memo);
    }

}