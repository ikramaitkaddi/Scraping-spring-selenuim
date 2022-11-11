package com.example.scrapingjava.service;


import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Models.Seciencedirectart;
import org.springframework.stereotype.Service;

@Service
public interface ScienceDirectService {
    public void save(Seciencedirectart scienceDirectart);
}
