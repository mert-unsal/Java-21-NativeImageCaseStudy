package com.munsal.java21demo.controller;


import com.munsal.java21demo.domain.model.StatisticInformationDto;
import com.munsal.java21demo.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(StatisticController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class StatisticController {
    public static final String BASE_PATH = "statistic";
    private static final String TAG = "Statistic Controllers";
    private final StatisticService statisticService;

    @GetMapping("monthlyStatistic")
    @Operation(tags = TAG, summary = "Serve monthly statistics")
    public List<StatisticInformationDto> getAllProducts() {
        return statisticService.getStatisticalInformation();
    }
}
