package com.oanda.learning.springreactordemo.services;

import com.oanda.learning.springreactordemo.entities.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IArticleService {
    Flux<Article> getArticles();
    Mono<Article> getArticleById(Long id);
    Mono<Article> saveArticle(Article articles);
    Mono<Void> deleteArticleById(Long id);
}
