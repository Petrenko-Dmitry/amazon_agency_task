package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.ReportTypeAndOptions;
import com.example.amazon_agency_task.entity.Statistic;

import java.util.List;
import java.util.stream.Collectors;

public record StatisticByAsinDTO(
        String id,
        ReportTypeAndOptions reportTypeAndOptions,
        String parentAsin,
        String childAsin,
        String sku,
        StatisticSalesByAsinDTO salesByAsin,
        StatisticTrafficByAsinDTO trafficByAsin
) {

    public StatisticByAsinDTO(Statistic statistic) {
        this(
                statistic.getId(),
                statistic.getReportTypeAndOptions(),
                statistic.getParentAsin(),
                statistic.getChildAsin(),
                statistic.getSku(),
                new StatisticSalesByAsinDTO(statistic.getSalesByAsin()),
                new StatisticTrafficByAsinDTO(statistic.getTrafficByAsin())
        );
    }

    public static List<StatisticByAsinDTO> convertStatistic(List<Statistic> statistics) {
        return statistics.stream()
                .map(StatisticByAsinDTO::new)
                .collect(Collectors.toList());
    }
}
