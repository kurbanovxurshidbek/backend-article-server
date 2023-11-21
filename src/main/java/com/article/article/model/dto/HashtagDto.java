package com.article.article.model.dto;

import com.article.article.model.entity.Hashtag;

public record HashtagDto(
        Long id,
        String hashtagName
) {

    public static HashtagDto of(String hashtagName) {
        return new HashtagDto(null, hashtagName);
    }

    public static HashtagDto of(Long id, String hashtagName) {
        return new HashtagDto(id, hashtagName);
    }

    public static HashtagDto from(Hashtag entity) {
        return new HashtagDto(
                entity.getId(),
                entity.getName()
        );
    }

    public Hashtag toEntity() {
        return Hashtag.of(hashtagName);
    }
}
