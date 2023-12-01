package com.article.article.model.request;

import com.article.article.model.dto.CommentDto;
import com.article.article.model.dto.UserAccountDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequest(
        @NotBlank(message = "username is required")
        @Size(min = 5, max = 20)
        String username,

        @NotNull(message = "articleId is required")
        @Size(min = 5, max = 20)
        Long articleId,

        @Size(min = 5, max = 20)
        Long parentCommentId,

        @NotBlank(message = "content is required")
        @Size(min = 5, max = 500)
        String content
) {
    public static CommentRequest of(String username, Long articleId, Long parentCommentId, String content) {
        return new CommentRequest(username, articleId, parentCommentId, content);
    }

    public CommentDto toDto(UserAccountDto userAccountDto) {
        return CommentDto.of(
                articleId,
                userAccountDto,
                parentCommentId,
                content
        );
    }
}
