package com.teho.crawling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest()
class CrawlingApplicationTests {
    @Autowired
    CrawlingService crawlingTest;


    @Test
    void crawling_test() {
        List<CrawlingService.RankInfo> crawling = crawlingTest.crawling(30);
        for (CrawlingService.RankInfo rankInfo : crawling) {
            System.out.println(rankInfo.getRank() + "위 " + rankInfo.getSinger() + " - " + rankInfo.getTitle());
        }

    }

}
