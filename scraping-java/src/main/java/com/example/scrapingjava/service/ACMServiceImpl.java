package com.example.scrapingjava.service;


import com.example.scrapingjava.Models.ACM;
import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Repository.AcmRepository;
import com.example.scrapingjava.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ACMServiceImpl implements ACMService {
    @Autowired
    AcmRepository acmRepository;


    @Override
    public void save(ACM acm) {
        acmRepository.save(acm);
    }
}
