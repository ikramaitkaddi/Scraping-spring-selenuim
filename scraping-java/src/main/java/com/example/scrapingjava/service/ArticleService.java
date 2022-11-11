package com.example.scrapingjava.service;

import com.example.scrapingjava.Models.Article;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    public void save(Article article);
}
