package com.example.scrapingjava.service;

import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }
}
