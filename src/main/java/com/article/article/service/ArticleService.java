package com.article.article.service;

import com.article.article.exception.ResourceNotFoundException;
import com.article.article.model.common.Header;
import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.HashtagDto;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.Article;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.ArticleRequest;
import com.article.article.model.response.ArticleResponse;
import com.article.article.repository.ArticleRepository;
import com.article.article.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;

    public ArticleResponse saveArticle(Header<ArticleRequest> dto) {
        ArticleRequest articleRequest = dto.getData();

        UserAccount userAccount = userAccountRepository.findByUserId(articleRequest.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserAccountDto userAccountDto = UserAccountDto.from(userAccount);

        Set<HashtagDto> hashtagDtos = renewHashtagsFromContent(articleRequest.content());
        ArticleDto articleDto = articleRequest.toDto(userAccountDto, hashtagDtos);

        Article article = articleDto.toEntity(userAccount);
        Article savedArticle = articleRepository.save(article);

        return ArticleResponse.from(ArticleDto.from(savedArticle));
    }

    private Set<HashtagDto> renewHashtagsFromContent(String content) {
        return new LinkedHashSet<>();
    }
}
