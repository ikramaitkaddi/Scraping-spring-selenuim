package com.example.scrapingjava.service;


import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Models.Seciencedirectart;
import com.example.scrapingjava.Repository.ArticleRepository;
import com.example.scrapingjava.Repository.ScienceDirectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScienceDirectServiceImpl  implements ScienceDirectService{

    @Autowired
    ScienceDirectRepository  scienceDirectRepository;
    @Override
    public void save(Seciencedirectart article) {
        scienceDirectRepository.save(article);
    }
}
