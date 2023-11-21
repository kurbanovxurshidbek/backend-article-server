package com.article.article.controller;

import com.article.article.model.common.Header;
import com.article.article.model.common.PaginationData;
import com.article.article.model.entity.Article;
import com.article.article.model.request.ArticleRequest;
import com.article.article.model.response.ArticleResponse;
import com.article.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public Header<?> saveArticle(@RequestBody Header<ArticleRequest> dto) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.saveArticle(dto));
        return Header.ok(articleResponse);
    }

    @GetMapping("/articles")
    public Header<?> getAllArticles(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<ArticleResponse> articlesPage = articleService.searchArticles(keyword, paging).map(ArticleResponse::from);

        return Header.ok(articlesPage.getContent(), PaginationData.build(articlesPage));
    }

    @GetMapping("/articles/{id}")
    public Header<?> getArticle(@PathVariable Long id) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.getArticle(id));
        return Header.ok(articleResponse);
    }

    @PutMapping("/articles/{id}")
    public Header<?> updateArticle(@PathVariable Long id, @RequestBody Header<ArticleRequest> dto) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.updateArticle(id, dto));
        return Header.ok(articleResponse);
    }

    @DeleteMapping("/articles/{id}")
    public Header<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return Header.ok();
    }
}
