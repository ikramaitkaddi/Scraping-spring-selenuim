package com.example.scrapingjava.service;


import com.example.scrapingjava.Models.ACM;
import com.example.scrapingjava.Models.Article;
import org.springframework.stereotype.Service;

@Service
public interface ACMService {
    public void save(ACM acm);
}
