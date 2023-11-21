package com.article.article.service;

import com.article.article.exception.ResourceNotFoundException;
import com.article.article.model.common.Header;
import com.article.article.model.dto.ArticleDto;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.Article;
import com.article.article.model.entity.Hashtag;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.ArticleRequest;
import com.article.article.repository.ArticleRepository;
import com.article.article.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final HashtagService hashtagService;

    public ArticleDto saveArticle(Header<ArticleRequest> dto) {
        ArticleRequest articleRequest = dto.getData();

        UserAccount userAccount = userAccountRepository.findByUserId(articleRequest.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        ArticleDto articleDto = articleRequest.toDto(UserAccountDto.from(userAccount));
        Set<Hashtag> hashtags = getHashtagsFromContent(articleDto.content());

        Article article = articleDto.toEntity(userAccount);
        article.addHashtags(hashtags);
        Article savedArticle = articleRepository.save(article);

        return ArticleDto.from(savedArticle);
    }

    public ArticleDto updateArticle(Long articleId, Header<ArticleRequest> dto) {
        ArticleRequest articleRequest = dto.getData();

        UserAccount userAccount = userAccountRepository.findByUserId(articleRequest.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
        article.setTitle(articleRequest.title());
        article.setContent(articleRequest.title());
        article.setUserAccount(userAccount);

        // get hashtag ids from article
        Set<Long> hashtagIds = article.getHashtags().stream()
                .map(Hashtag::getId)
                .collect(Collectors.toUnmodifiableSet());
        // clear hashtags from article
        article.clearHashtags();

        // delete hashtags from db
        hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);

        // reset new hashtags
        Set<Hashtag> hashtags = getHashtagsFromContent(articleRequest.content());
        article.addHashtags(hashtags);

        Article updatedArticle = articleRepository.save(article);
        return ArticleDto.from(updatedArticle);
    }

    private Set<Hashtag> getHashtagsFromContent(String content) {
        // Save hashtag if not exist
        Set<String> hashtagNamesInContent = hashtagService.parseHashtagNames(content);
        for (String name : hashtagNamesInContent) {
            hashtagService.saveHashtag(name);
        }

        return hashtagService.findHashtagsByNames(hashtagNamesInContent);
    }

    public Page<ArticleDto> searchArticles(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return articleRepository.findByTitleContaining(keyword, pageable).map(ArticleDto::from);
    }

    public ArticleDto getArticle(Long id) {
        return articleRepository.findById(id).map(ArticleDto::from).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
    }

    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found"));
        Set<Long> hashtagIds = article.getHashtags().stream()
                .map(Hashtag::getId)
                .collect(Collectors.toUnmodifiableSet());

        articleRepository.deleteById(id);
        hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);
    }
}
