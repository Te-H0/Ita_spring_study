package com.teho.crawling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @ResponseBody
    @GetMapping(value = {"/", "/{rank}"}, produces = "application/json")
    public List<CrawlingService.RankInfo> mellonChartAPI(@PathVariable(required = false) Integer rank) {
        if (rank == null) {
            rank = 30;
            log.info("rank => {}", rank);
        }
        return crawlingService.crawling(rank);
    }

    @GetMapping(value = {"/", "/{rank}"})
    public String mellonChart(@PathVariable(required = false) Integer rank, Model model) {
        if (rank == null) {
            rank = 30;
            log.info("rank => {}", rank);
        }
        List<CrawlingService.RankInfo> rankList = crawlingService.crawling(rank);
        model.addAttribute("rankInfo", rankList);

        return "melonRank.html";
    }

    @GetMapping("/test")
    public String test() {
        log.info("왜 안돼?");
        return "melonRank.html";
    }
}
