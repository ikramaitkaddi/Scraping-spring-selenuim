package com.example.scrapingjava.Repository;

import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Models.Seciencedirectart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScienceDirectRepository extends MongoRepository<Seciencedirectart, String> {
}
