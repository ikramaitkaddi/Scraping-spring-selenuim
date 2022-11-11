package com.example.scrapingjava.scrape;


import com.example.scrapingjava.Models.ACM;
import com.example.scrapingjava.Models.Article;
import com.example.scrapingjava.service.ACMService;
import com.example.scrapingjava.service.ArticleService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Controller
public class AcmController {

    @Autowired
    ACMService acmService;

    int page = 1;


    //@PostConstruct
    public  void Acmscrape() {

        File file = new File("C:/Users/Dell/Downloads/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        WebDriver driver = new ChromeDriver();


        driver.get("https://dl.acm.org/action/doSearch?AllField=Blockchain");
        try {
            do {
            Thread.sleep(3000);

            final WebElement words = driver.findElement(By.className("items-results"));
            final List<WebElement> articleList = words.findElements(By.className("issue-item-container"));


            articleList.forEach(
                    article -> {
                        ACM acm = new ACM();
                        final WebElement title = article.findElement(By.className("issue-item__title"));
                        //System.out.println(title.getText());
                        acm.setTitle(title.getText());
                        final WebElement pubDate = article.findElement(By.className("bookPubDate"));
                        //System.out.println(pubDate.getText());
                        acm.setPublishIn(pubDate.getText());
                        if(article.findElements(By.className("count-list")).size() != 0  ){
                            final WebElement plus = article.findElement(By.className("count-list"));
                            if( article.findElement(By.className("rlist--inline")).getText().contains("+")) {
                                final WebElement authors = article.findElement(By.className("rlist--inline"));
                                /*System.out.println(authors.getText().split("[0-9]")[0].replace(", +",""));   */
                                acm.setAuthors(authors.getText().split("[0-9]")[0].replace(", +",""));
                            }
                        }else{
                            final WebElement authors = article.findElement(By.className("rlist--inline"));
                          //  System.out.println(authors.getText());
                            acm.setAuthors(authors.getText());
                        }
                        //epub-section__title
                        final WebElement journal = article.findElement(By.className("epub-section__title"));
                        //System.out.println(journal.getText());
                        acm.setJournal(journal.getText());
                        final WebElement dateText = article.findElement(By.className("dot-separator"));
                        final List<WebElement> Listspan =dateText.findElements(By.tagName("span"));
                        //System.out.println(Listspan.get(0).getText());
                        acm.setDate(Listspan.get(0).getText());
                        //System.out.println(Listspan.get(1).getText().replace("pp ",""));
                        acm.setPages(Listspan.get(1).getText().replace("pp ",""));
                        final WebElement url = article.findElement(By.className("issue-item__doi"));
                        //System.out.println(url.getText());
                        acm.setUrl(url.getText());
                        final WebElement abstractTExt = article.findElement(By.className("issue-item__abstract"));
                        System.out.println(abstractTExt.getText());
                        acm.setAbstractText(abstractTExt.getText());
                        acmService.save(acm);
                    }

            );

                page ++;
                driver.navigate().to("https://dl.acm.org/action/doSearch?AllField=Blockchain&startPage=0" +page);
                Thread.sleep(2000);
            }while(driver.findElements(By.className("pagination__btn--next")).size() != 0);
        }catch(WebDriverException | InterruptedException e){
            System.out.println("Exeption");
        }
        driver.close();
        }
    }


