package com.article.article.controller;

import com.article.article.model.common.Header;
import com.article.article.model.common.PaginationData;
import com.article.article.model.request.CommentRequest;
import com.article.article.model.response.CommentResponse;
import com.article.article.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public Header<?> saveComment(@RequestBody Header<CommentRequest> dto) {
        CommentResponse commentResponse = CommentResponse.from(commentService.saveComment(dto));
        return Header.ok(commentResponse);
    }

    @DeleteMapping("/comments/{id}")
    public Header<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return Header.ok();
    }

    @GetMapping("/comments")
    public Header<?> getAllComments(@RequestParam Long articleId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CommentResponse> commentsPage = commentService.getComments(articleId, paging).map(CommentResponse::from);

        return Header.ok(commentsPage.getContent(), PaginationData.build(commentsPage));
    }

}
