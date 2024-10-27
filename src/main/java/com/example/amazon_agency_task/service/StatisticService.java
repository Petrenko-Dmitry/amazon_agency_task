package com.example.amazon_agency_task.service;

import com.example.amazon_agency_task.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getStatisticsByDate(String startDate, String endDate);
    List<Statistic> getStatisticsByDate(String date);
    List<Statistic> getStatisticsByAsins(List<String> asins);
    Statistic getStatisticsByAllDates();
    Statistic getStatisticsByAllAsins();
}
