package com.example.amazon_agency_task.service.impl;

import com.example.amazon_agency_task.entity.Statistic;
import com.example.amazon_agency_task.repository.StatisticRepository;
import com.example.amazon_agency_task.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Cacheable(value = "statisticsByDate", key = "#startDate + '_' + #endDate")
    @Override
    public List<Statistic> getStatisticsByDate(String startDate, String endDate) {
        return this.statisticRepository.findByDateBetween(startDate, endDate);
    }

    @Cacheable(value = "statisticsByDate", key = "#date")
    @Override
    public List<Statistic> getStatisticsByDate(String date) {
        return this.statisticRepository.findByDate(date);
    }

    @Cacheable(value = "statisticsByAsin", key = "#asins")
    @Override
    public List<Statistic> getStatisticsByAsins(List<String> asins) {
        return this.statisticRepository.findByAsinIn(asins);
    }

    @Cacheable(value = "statisticsByAllDates")
    @Override
    public Statistic getStatisticsByAllDates() {
        return this.statisticRepository.aggregateStatisticByAllDates();
    }

    @Cacheable(value = "statisticsByAllAsins")
    @Override
    public Statistic getStatisticsByAllAsins() {
        return this.statisticRepository.aggregateStatisticByAllAsins();
    }
}

