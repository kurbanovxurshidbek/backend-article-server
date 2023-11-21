package com.article.article.model.response;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

public record UserAccountResponse(
        String userId,
        String email,
        String nickname,
        String memo
) {

    public static UserAccountResponse of(String userId, String email, String nickname, String memo) {
        return new UserAccountResponse(userId, email, nickname, memo);
    }

    public static UserAccountResponse from(UserAccountDto dto) {
        return new UserAccountResponse(
                dto.userId(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

}