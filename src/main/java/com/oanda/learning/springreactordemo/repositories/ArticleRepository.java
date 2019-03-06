package com.oanda.learning.springreactordemo.repositories;

import com.oanda.learning.springreactordemo.entities.Article;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ArticleRepository extends ReactiveCrudRepository<Article, Long> {
}
