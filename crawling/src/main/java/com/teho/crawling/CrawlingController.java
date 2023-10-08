package com.teho.crawling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping(value = {"/", "/{rank}"})
    public List<CrawlingService.RankInfo> mellonChart(@PathVariable(required = false) Integer rank) {
        if (rank == null) {
            rank = 30;
            log.info("rank => {}", rank);
        }
        List<CrawlingService.RankInfo> rankList = crawlingService.crawling(rank);
        log.info("rank list => {}", rankList);
        return rankList;
    }


}
