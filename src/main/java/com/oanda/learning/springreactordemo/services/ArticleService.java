package com.oanda.learning.springreactordemo.services;

import com.oanda.learning.springreactordemo.entities.Article;
import com.oanda.learning.springreactordemo.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleService implements IArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Flux<Article> getArticles(){
        return articleRepository.findAll();
    }

    @Override
    public Mono<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Mono<Article> saveArticle(Article article){
        return articleRepository.save(article);
    }

    @Override
    public Mono<Void> deleteArticleById(Long id){
        return articleRepository.deleteById(id);
    }
}
