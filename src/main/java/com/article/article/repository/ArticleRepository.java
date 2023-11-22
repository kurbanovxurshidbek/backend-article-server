package com.article.article.repository;

import com.article.article.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    Page<Article> findByTitleContaining(String title, Pageable pageable);

    Page<Article> findByContentContaining(String content, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);


}
