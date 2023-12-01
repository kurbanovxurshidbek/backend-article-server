package com.article.article.model.response;

import com.article.article.model.dto.CommentDto;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record CommentResponse(
        Long id,
        String content,
        String nickname,
        String username,
        Long parentCommentId,
        Set<CommentResponse> childComments
) {
    public static CommentResponse of(Long id, String content, String nickname, String username) {
        return CommentResponse.of(id, content, nickname, username, null);
    }

    public static CommentResponse of(Long id, String content, String nickname, String username, Long parentCommentId) {
        Comparator<CommentResponse> childCommentComparator = Comparator
                .comparing(CommentResponse::content)
                .thenComparingLong(CommentResponse::id);

        return new CommentResponse(id, content, nickname, username, parentCommentId, new TreeSet<>(childCommentComparator));
    }

    public static CommentResponse from(CommentDto dto) {

        return CommentResponse.of(
                dto.id(),
                dto.content(),
                dto.userAccountDto().nickname(),
                dto.userAccountDto().username(),
                dto.parentCommentId()
        );
    }

    public boolean hasParentComment() {
        return parentCommentId != null;
    }
}
