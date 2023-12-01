package com.article.article.model.request;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record ArticleRequest(
        @NotBlank(message = "userId is required")
        @Size(min = 5, max = 20)
        String username,

        @NotBlank(message = "title is required")
        @Size(min = 5, max = 20)
        String title,
        @NotBlank(message = "content is required")
        @Size(min = 5, max = 500)
        String content
) {
    public static ArticleRequest of(String username, String title, String content) {
        return new ArticleRequest(username, title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtagDtos
        );
    }
}
