package com.example.amazon_agency_task.scheduler;

import com.example.amazon_agency_task.domain.ReportTypeAndOptions;
import com.example.amazon_agency_task.entity.Statistic;
import com.example.amazon_agency_task.repository.StatisticRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.amazon_agency_task.constants.StringConstants.*;

@Component
@Slf4j
public class StatisticScheduler {

    @Autowired
    private StatisticRepository statisticRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 * * * * *")
    public void updateStatistics() {
        try {
            var jsonNode = objectMapper.readTree(new File("test_report.json"));
            var reportOptions = objectMapper.convertValue(jsonNode.get(REPORT_SPECIFICATION), new TypeReference<ReportTypeAndOptions>() {});

            var allSales = new ArrayList<>(processSales(jsonNode.get(SALES_BY_DATE), reportOptions, true));
            allSales.addAll(processSales(jsonNode.get(SALES_BY_ASIN), reportOptions, false));

            this.statisticRepository.saveAll(allSales);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private List<Statistic> processSales(JsonNode salesNode, ReportTypeAndOptions reportOptions, boolean byDate) {
        var sales = objectMapper.convertValue(salesNode, new TypeReference<List<Statistic>>() {});
        var existsSales = byDate ?
                this.statisticRepository.findByDateAndOptions(
                        sales.stream()
                                .map(Statistic::getDate)
                                .collect(Collectors.toList()),
                        reportOptions
                ) :
                this.statisticRepository.findByAsinAndOptions(
                        sales.stream()
                                .map(Statistic::getParentAsin)
                                .collect(Collectors.toList()),
                        reportOptions
                );

        sales.forEach(sale -> {
            var existsSale = existsSales.stream()
                    .filter(existing -> byDate ? existing.getDate().equals(sale.getDate()) : existing.getParentAsin().equals(sale.getParentAsin()))
                    .findFirst()
                    .orElseGet(Statistic::new);

            sale.setId(existsSale.getId());
            sale.setReportTypeAndOptions(reportOptions);
        });

        return sales;
    }
}
