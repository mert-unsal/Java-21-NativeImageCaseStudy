package com.munsal.java21demo.service;


import com.munsal.java21demo.domain.model.StatisticInformationDto;
import com.munsal.java21demo.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public List<StatisticInformationDto> getStatisticalInformation() {
        return statisticRepository.getStatisticInformation();
    }
}
