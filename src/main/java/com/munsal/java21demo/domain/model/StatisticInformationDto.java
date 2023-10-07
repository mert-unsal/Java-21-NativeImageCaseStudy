package com.munsal.java21demo.domain.model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StatisticInformationDto {
    private String month;
    private String year;
    private Long totalOrderCount;
    private Long totalBookCount;
    private BigDecimal totalAmountPerMonth;
}
