package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.StatisticTrafficByAsin;

import java.math.BigDecimal;

public record StatisticTrafficByAsinDTO(
        Integer browserSessions,
        Integer browserSessionsB2B,
        Integer mobileAppSessions,
        Integer mobileAppSessionsB2B,
        Integer sessions,
        Integer sessionsB2B,
        BigDecimal browserSessionPercentage,
        BigDecimal browserSessionPercentageB2B,
        BigDecimal mobileAppSessionPercentage,
        BigDecimal mobileAppSessionPercentageB2B,
        BigDecimal sessionPercentage,
        BigDecimal sessionPercentageB2B,
        Integer browserPageViews,
        Integer browserPageViewsB2B,
        Integer mobileAppPageViews,
        Integer mobileAppPageViewsB2B,
        Integer pageViews,
        Integer pageViewsB2B,
        BigDecimal browserPageViewsPercentage,
        BigDecimal browserPageViewsPercentageB2B,
        BigDecimal mobileAppPageViewsPercentage,
        BigDecimal mobileAppPageViewsPercentageB2B,
        BigDecimal pageViewsPercentage,
        BigDecimal pageViewsPercentageB2B,
        BigDecimal buyBoxPercentage,
        BigDecimal buyBoxPercentageB2B,
        BigDecimal unitSessionPercentage,
        BigDecimal unitSessionPercentageB2B
) {
    public StatisticTrafficByAsinDTO(StatisticTrafficByAsin statistic) {
        this(
                statistic.getBrowserSessions(),
                statistic.getBrowserSessionsB2B(),
                statistic.getMobileAppSessions(),
                statistic.getMobileAppSessionsB2B(),
                statistic.getSessions(),
                statistic.getSessionsB2B(),
                statistic.getBrowserSessionPercentage(),
                statistic.getBrowserSessionPercentageB2B(),
                statistic.getMobileAppSessionPercentage(),
                statistic.getMobileAppSessionPercentageB2B(),
                statistic.getSessionPercentage(),
                statistic.getSessionPercentageB2B(),
                statistic.getBrowserPageViews(),
                statistic.getBrowserPageViewsB2B(),
                statistic.getMobileAppPageViews(),
                statistic.getMobileAppPageViewsB2B(),
                statistic.getPageViews(),
                statistic.getPageViewsB2B(),
                statistic.getBrowserPageViewsPercentage(),
                statistic.getBrowserPageViewsPercentageB2B(),
                statistic.getMobileAppPageViewsPercentage(),
                statistic.getMobileAppPageViewsPercentageB2B(),
                statistic.getPageViewsPercentage(),
                statistic.getPageViewsPercentageB2B(),
                statistic.getBuyBoxPercentage(),
                statistic.getBuyBoxPercentageB2B(),
                statistic.getUnitSessionPercentage(),
                statistic.getUnitSessionPercentageB2B()
        );
    }
}
