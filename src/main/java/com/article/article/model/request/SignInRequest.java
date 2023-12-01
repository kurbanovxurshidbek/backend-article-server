package com.article.article.model.request;

import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record SignInRequest(
        @NotBlank(message = "username is required")
        @Email
        String username,

        @NotBlank(message = "password is required")
        @Size(min = 5, max = 20)
        String password
) {

    public static SignInRequest of(String username, String password) {
        return new SignInRequest(username, password);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(null, username, password, null);
    }

}