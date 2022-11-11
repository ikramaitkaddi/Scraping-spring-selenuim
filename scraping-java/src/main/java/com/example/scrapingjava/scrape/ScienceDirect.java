package com.example.scrapingjava.scrape;


import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Models.Seciencedirectart;
import com.example.scrapingjava.Repository.ScienceDirectRepository;
import com.example.scrapingjava.service.ScienceDirectService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Controller
public class ScienceDirect {

    int page = 0;
    String a="";
    @Autowired
    ScienceDirectService scienceDirectService;
    //@PostConstruct
    public void scienceScrape() throws InterruptedException {
        File file = new File("C:/Users/Dell/Downloads/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        WebDriver driver = new ChromeDriver();


        driver.get("https://www.sciencedirect.com/search?qs=Blockchain");
        try {
            do {
            Thread.sleep(2000);
            System.out.println("Page Title:" + driver.getTitle());
            final WebElement content = driver.findElement(By.className("search-result-wrapper"));

            final List<WebElement> articleList = content.findElements(By.className("ResultItem"));
           // final List<WebElement> articleList = content.findElements(By.className("result-list-title-link"));
            articleList.forEach(
                    article -> {
                        Seciencedirectart art = new  Seciencedirectart ();
                       // System.out.println(article.findElement(By.className("result-list-title-link")).getText());
                        art.setTitle(article.findElement(By.className("result-list-title-link")).getText());
                        final WebElement journal = article.findElement(By.className("subtype-srctitle-link"));
                        final WebElement date = article.findElement(By.className("srctitle-date-fields"));
                        //System.out.println(journal.getText());
                        art.setJournal(journal.getText());
                        final List<WebElement> Listspan = date.findElements(By.tagName("span"));
                       // System.out.println("dateee" +Listspan.get(3).getText());
                        art.setDate(Listspan.get(3).getText());
                        final WebElement Authors = article.findElement(By.className("Authors"));
                        final List<WebElement> authorList = Authors.findElements(By.tagName("li"));
                        System.out.println("in article");

                        authorList.forEach(
                                author -> {

                                    final WebElement auth = author.findElement(By.className("author"));
                                    a = a+ author.getText() + ";";
                                    System.out.println(author.getText() + ";");

                                }
                        );
                        art.setAuthors(a);
                         a="";
                        scienceDirectService.save(art);
                    }
            );

            Thread.sleep(4000);
                page = page +25;
            driver.navigate().to("https://www.sciencedirect.com/search?qs=Blockchain%20Sa&offset=" +page);
                Thread.sleep(3000);
                System.out.println("----------------------------In another page------------------------------");
            }while(driver.findElements(By.className("next-link")).size() != 0);
        }catch(WebDriverException | InterruptedException e){
        System.out.println("Exeption");
        }
        driver.close();

    }

}
