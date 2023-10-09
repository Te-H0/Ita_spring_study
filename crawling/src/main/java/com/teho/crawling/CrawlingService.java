package com.teho.crawling;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CrawlingService {
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "/Users/te___ho/Desktop/teho/Ita_sping_study/crawling/src/main/resources/static/chromedriver_arm64/chromedriver";
    private static final String url = "https://www.melon.com/chart/index.htm";// 크롤링 할 사이트
    private WebDriver driver;

    public List<RankInfo> crawling(int count) {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking"); //팝업 x
        options.addArguments("headless");//브라우저 x
        options.addArguments("--disable-gpu");//gpu 비활성화
        options.addArguments("-blink-settings=imagesEnabled=false"); //이미지 다운 x

        driver = new ChromeDriver(options);
        driver.get(url);
        List<RankInfo> chart = new ArrayList<>();
        try {

            ExpectedConditions.presenceOfElementLocated(By.className("rank01"));
            //Thread.sleep(5000);
            log.info("crawling Start");
            List<WebElement> titleElements = driver.findElements(By.className("rank01"));
            List<WebElement> singerElements = driver.findElements(By.className("rank02"));
            Iterator<WebElement> titleIter = titleElements.iterator();
            Iterator<WebElement> singerIter = singerElements.iterator();

            for (int i = 1; i <= count; i++) {

                WebElement singer = singerIter.next();
                WebElement title = titleIter.next();
                RankInfo rankInfo = new RankInfo(i, title.getText(), singer.getText());
                chart.add(rankInfo);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
            log.info("finish crawling");
        }
        return chart;
    }

    @Getter
    public static class RankInfo {
        public String title;
        public String singer;
        int rank;

        public RankInfo(int rank, String title, String singer) {
            this.rank = rank;
            this.title = title;
            this.singer = singer;
        }
    }
}
