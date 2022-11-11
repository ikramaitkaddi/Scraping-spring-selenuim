package com.example.scrapingjava.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.scrapingjava.Models.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
}
