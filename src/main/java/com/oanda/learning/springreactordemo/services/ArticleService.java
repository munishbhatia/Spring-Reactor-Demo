package com.oanda.learning.springreactordemo.services;

import com.oanda.learning.springreactordemo.entities.Article;
import com.oanda.learning.springreactordemo.repositories.ArticleRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleService implements IArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ExternalService externalService;
    private CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("ExternalSvc");

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

    public Mono<String> getSampleStringFromExternalSvc(){
//        return Mono.fromCallable(externalService::getSampleString).transform(CircuitBreakerOperator.of(circuitBreaker));
        return Mono.from(externalService.getSampleString()).transform(CircuitBreakerOperator.of(circuitBreaker));
    }

    public Flux<String> getStringsFromExternalSvc(){
        System.out.println(circuitBreaker.getState());
        return Flux.from(externalService.getStringsFromSvc()).transform(CircuitBreakerOperator.of(circuitBreaker));
    }
}
