package com.article.article.model.response;

import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.CommentDto;
import com.article.article.model.dto.HashtagDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public record CommentResponse(
        Long id,
        String content,
        String email,
        String nickname,
        String userId,
        Long parentCommentId,
        Set<CommentResponse> childComments
) {
    public static CommentResponse of(Long id, String content, String email, String nickname, String userId) {
        return CommentResponse.of(id, content, email, nickname, userId, null);
    }

    public static CommentResponse of(Long id, String content, String email, String nickname, String userId, Long parentCommentId) {
        Comparator<CommentResponse> childCommentComparator = Comparator
                .comparing(CommentResponse::content)
                .thenComparingLong(CommentResponse::id);

        return new CommentResponse(id, content, email, nickname, userId, parentCommentId, new TreeSet<>(childCommentComparator));
    }

    public static CommentResponse from(CommentDto dto) {

        return CommentResponse.of(
                dto.id(),
                dto.content(),
                dto.userAccountDto().email(),
                dto.userAccountDto().nickname(),
                dto.userAccountDto().userId(),
                dto.parentCommentId()
        );
    }

    public boolean hasParentComment() {
        return parentCommentId != null;
    }
}
