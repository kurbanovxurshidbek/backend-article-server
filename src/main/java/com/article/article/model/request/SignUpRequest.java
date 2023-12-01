package com.article.article.model.request;

import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record SignUpRequest(
        @NotBlank(message = "username is required")
        @Email
        String username,

        @NotBlank(message = "password is required")
        @Size(min = 5, max = 20)
        String password,

        @NotBlank(message = "nickname is required")
        @Size(min = 5, max = 20)
        String nickname
) {

    public static SignUpRequest of(String username, String password, String nickname) {
        return new SignUpRequest(username, password, nickname);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(null, username, password, nickname);
    }

}