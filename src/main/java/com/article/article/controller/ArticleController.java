package com.article.article.controller;

import com.article.article.model.common.Header;
import com.article.article.model.request.ArticleRequest;
import com.article.article.model.response.ArticleResponse;
import com.article.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public Header<?> saveArticle(@RequestBody Header<ArticleRequest> dto) {
        ArticleResponse articleResponse = articleService.saveArticle(dto);
        return Header.ok(articleResponse);
    }
}
