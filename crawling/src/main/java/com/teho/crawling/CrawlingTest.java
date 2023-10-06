package com.teho.crawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class CrawlingTest {
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/Users/te___ho/Desktop/teho/Ita_sping_study/crawling/src/main/resources/static/chromedriver_arm64/chromedriver";
    private static final String url = "https://www.melon.com/chart/index.htm";// 크롤링 할 사이트
    private WebDriver driver;

    public void crawling() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(url);

        try {
            Thread.sleep(5000);
            List<WebElement> titleElements = driver.findElements(By.className("rank01"));
            List<WebElement> singerElements = driver.findElements(By.className("rank02"));
            Iterator<WebElement> titleIter = titleElements.iterator();
            Iterator<WebElement> singerIter = singerElements.iterator();

            for (int count = 1; count < 30; count++) {
                System.out.print(count + "위 ");
                WebElement singer = singerIter.next();
                WebElement title = titleIter.next();

                System.out.print(singer.getText() + " - ");
                System.out.println(title.getText());

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            log.info("finish crawling");
            driver.close();
            driver.quit();
            log.info("here?");
        }
    }
}
