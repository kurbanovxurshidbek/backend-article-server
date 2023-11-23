package com.article.article.model.response;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.CommentDto;
import com.article.article.model.dto.HashtagDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CommentResponse(
        Long id,
        String content,
        String email,
        String nickname,
        String userId
) {
    public static CommentResponse of(Long id, String content, String email, String nickname, String userId) {
        return new CommentResponse(id, content, email, nickname, userId);
    }

    public static CommentResponse from(CommentDto dto) {

        return new CommentResponse(
                dto.id(),
                dto.content(),
                dto.userAccountDto().email(),
                dto.userAccountDto().nickname(),
                dto.userAccountDto().userId()
        );
    }
}
