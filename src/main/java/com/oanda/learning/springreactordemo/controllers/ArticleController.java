package com.oanda.learning.springreactordemo.controllers;

import com.oanda.learning.springreactordemo.entities.Article;
import com.oanda.learning.springreactordemo.services.ArticleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.HttpURLConnection;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    @ApiOperation(value = "Get all customers", notes = "Get all customers matching the given search string.", responseContainer = "List", response = Article.class, produces = "http, https")
    public Flux<Article> getArticles(){
        return articleService.getArticles();
    }

    @GetMapping(value = "/api/articles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Article> getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @PostMapping(value = "/api/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code=202,message="ACCEPTED" ),
            @ApiResponse(code=HttpURLConnection.HTTP_CREATED,response=Article.class, responseContainer="Mono", message="CREATED"),
            @ApiResponse(code=HttpURLConnection.HTTP_INTERNAL_ERROR, message="INTERNAL_SERVER_ERROR")})
    public Mono<Article> saveArticle(@RequestBody Article article){
        return articleService.saveArticle(article).subscribeOn(Schedulers.elastic());//.subscribe(article1 -> {article.toString();});//.single();
    }

    @DeleteMapping(value = "/api/articles/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteArticleById(Long id){
        return articleService.deleteArticleById(id);
    }
}
