package com.article.article.repository;

import com.article.article.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByArticle_Id(Long articleId, Pageable pageable);
    void deleteByIdAndUserAccount_Username(Long id, String username);
}
