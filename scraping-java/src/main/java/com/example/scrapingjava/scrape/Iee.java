package com.example.scrapingjava.scrape;

import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.Repository.ArticleRepository;
import com.example.scrapingjava.service.ArticleService;
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
public class Iee {
    @Autowired
    ArticleRepository articleService;


    int page = 1;
    String Authors ;

    @PostConstruct
    public  void ieescrape(){
        File file = new File("C:/Users/Dell/Downloads/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        WebDriver driver = new ChromeDriver();


        driver.get("https://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText=blockchain");
        try {
            do {
                Thread.sleep(3000);
                final WebElement words = driver.findElement(By.className("main-section"));
                final List<WebElement> articleList = words.findElements(By.className("List-results-items"));
                articleList.forEach(
                        article -> {
                            Article art = new Article();
                            try {
                                final WebElement titre = article.findElement(By.tagName("h2"));
                                final WebElement lien = titre.findElement(By.tagName("a"));
                                art.setTitle(titre.findElement(By.tagName("a")).getText());
                                // titre.findElement(By.linkText(lien.getText())).click();
                                // System.out.println(lien.getAttribute("href"));
                                driver.navigate().to(lien.getAttribute("href"));


                                Thread.sleep(3000);

                            } catch (InterruptedException | WebDriverException e) {
                                // driver.navigate().back();
                            }

                            if (driver.findElements(By.className("abstract-desktop-div")).size() != 0) {
                                final WebElement abstractDiv = driver.findElement(By.className("abstract-desktop-div"));
                                //System.out.println("Page abstract:" + abstractDiv.findElement(By.className("u-mb-1")).findElement(By.tagName("div")).getText());
                                art.setAbstraText(abstractDiv.findElement(By.className("u-mb-1")).findElement(By.tagName("div")).getText());
                            }


                            if (driver.findElements(By.className("stats-document-abstract-publishedIn")).size() != 0) {
                                final WebElement publishDiv = driver.findElement(By.className("stats-document-abstract-publishedIn"));
                                art.setPublichedIn(publishDiv.findElement(By.tagName("a")).getText());
                                //System.out.println("publishIn:" + abstractDiv.findElement(By.tagName("a")).getText());
                            }
                            if (driver.findElements(By.className("doc-abstract-confdate")).size() != 0) {
                                String date = driver.findElement(By.className("doc-abstract-confdate")).getText();
                                art.setDate(date.replace("Date of Conference: ", ""));
                                //System.out.println("date:" + date.replace("Date of Conference: ", ""));
                            }
                            if (driver.findElements(By.className("stats-document-abstract-doi")).size() != 0) {
                                String doi = driver.findElement(By.className("stats-document-abstract-doi")).getText();
                                art.setDOI(doi.replace("DOI: ", ""));
                                //System.out.println("doi" + doi.replace("DOI: ", ""));
                            }
                            if (driver.findElements(By.className("doc-abstract-conferenceLoc")).size() != 0) {
                                String location = driver.findElement(By.className("doc-abstract-conferenceLoc")).getText();
                                art.setLocation(location.replace("Conference Location: ", ""));
                                System.out.println("loc:" + location.replace("Conference Location: ", ""));
                            }


                            driver.navigate().back();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                            //System.out.println(article.getText());
                            List<WebElement> authorList = article.findElements(By.className("author"));
                            authorList.forEach(author ->this.Authors = this.Authors + ";" +author.getText());
                            art.setOthors(this.Authors);
                            articleService.save(art);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
                // WebElement nextButton = driver.findElement(By.className("next-btn"));
                  //nextButton.findElement(By.tagName("a")).click();
                page ++;
                driver.navigate().to("https://ieeexplore.ieee.org/search/searchresult.jsp?queryText=blockchain&pageNumber=" +page);
                Thread.sleep(2000);
            }while(driver.findElements(By.className("next-btn")).size() != 0);
            //final List<WebElement> wordlist = words.findElements(By.tagName("h2"));
            //wordlist.forEach(word ->System.out.println(word.getText()));

            System.out.println(" Count: " + driver.findElement(By.className("main-section")));
            System.out.println("Page Title:" + driver.getTitle());

            Thread.sleep(4000);
            //
            // Close the browser
            //
        }catch(WebDriverException | InterruptedException ee){
            System.out.println("Exeption");

        }
        driver.close();
    }
}
