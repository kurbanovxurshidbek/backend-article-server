package com.article.article.model.request;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import com.article.article.model.dto.UserAccountDto;
import java.util.Set;

public record ArticleRequest(
        String title,
        String content
) {
    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
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
