package com.article.article.model.response;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        Set<String> hashtags,
        String email,
        String nickname
) {
    public static ArticleResponse of(Long id, String title, String content, Set<String> hashtags, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtags, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().username();
        }

        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtagDtos().stream()
                        .map(HashtagDto::hashtagName)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                dto.userAccountDto().username(),
                nickname
        );
    }
}
