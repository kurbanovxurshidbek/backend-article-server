package com.article.article.model.dto;

import com.article.article.model.entity.Article;
import com.article.article.model.entity.Comment;
import com.article.article.model.entity.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        String content
) {
    public static CommentDto of(Long articleId, UserAccountDto userAccountDto, String content) {
        return new CommentDto(null, articleId, userAccountDto, content);
    }

    public static CommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content) {
        return new CommentDto(id, articleId, userAccountDto, content);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent()
        );
    }

    public Comment toEntity(Article article, UserAccount userAccount) {
        return Comment.of(
                article,
                userAccount,
                content
        );
    }
}
