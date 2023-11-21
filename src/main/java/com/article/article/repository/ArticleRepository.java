package com.article.article.repository;

import com.article.article.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    Page<Article> findByTitleContaining(String title, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(Long articleId, String userId);

}
