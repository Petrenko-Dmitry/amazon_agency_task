package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.ReportTypeAndOptions;
import com.example.amazon_agency_task.entity.Statistic;

import java.util.List;
import java.util.stream.Collectors;

public record StatisticByDateDTO(
        String id,
        ReportTypeAndOptions reportTypeAndOptions,
        String date,
        StatisticSalesByDateDTO salesByDate,
        StatisticTrafficByDateDTO trafficByDate
) {
    public StatisticByDateDTO(Statistic statistic) {
        this(
                statistic.getId(),
                statistic.getReportTypeAndOptions(),
                statistic.getDate(),
                new StatisticSalesByDateDTO(statistic.getSalesByDate()),
                new StatisticTrafficByDateDTO(statistic.getTrafficByDate())
        );
    }

    public static List<StatisticByDateDTO> convertStatistic(List<Statistic> statistics) {
        return statistics.stream()
                .map(StatisticByDateDTO::new)
                .collect(Collectors.toList());
    }
}
