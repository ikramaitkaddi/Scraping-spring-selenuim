package com.example.scrapingjava;

import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.scrape.Iee;
import com.example.scrapingjava.scrape.ScienceDirect;
import com.example.scrapingjava.service.ArticleService;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ScreenshotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.net.URL;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
@SpringBootApplication
public class ScrapingJavaApplication {
   // @Autowired
   // ArticleService articleService;

    public static void main(String[] args) {
        SpringApplication.run(ScrapingJavaApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {

        return (args) -> {
            //Iee iee = new Iee();
            //iee.ieescrape();*
            //ScienceDirect sc = new ScienceDirect();
            //sc.scienceScrape();
        };
    }
}
