package com.example.scrapingjava.Repository;

import com.example.scrapingjava.Models.ACM;
import com.example.scrapingjava.Models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcmRepository extends MongoRepository<ACM, String> {
}
