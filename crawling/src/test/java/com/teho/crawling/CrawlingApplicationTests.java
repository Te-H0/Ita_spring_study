package com.teho.crawling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
class CrawlingApplicationTests {
    @Autowired
    CrawlingTest crawlingTest;


    @Test
    void crawling_test() {
        crawlingTest.crawling();
    }

}
