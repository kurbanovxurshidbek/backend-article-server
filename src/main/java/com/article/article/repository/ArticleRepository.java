package com.article.article.repository;

import com.article.article.model.entity.Article;
import com.article.article.model.entity.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    Page<Article> findByTitleContaining(String title, Pageable pageable);

    Page<Article> findByContentContaining(String content, Pageable pageable);

    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);

    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

    @Query(nativeQuery = true, value = """
               select
                   A.id,
                   A.user_id,
                   A.title,
                   A.content,
                   A.created_at,
                   A.created_by,
                   A.modified_at,
                   A.modified_by
               from article as A
               inner join article_hashtag AH
               on A.id = AH.article_id
               inner join hashtag H
               on AH.hashtag_id = H.id
               where H.name  in (:hashtagNames);
            """)
    Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);

}
