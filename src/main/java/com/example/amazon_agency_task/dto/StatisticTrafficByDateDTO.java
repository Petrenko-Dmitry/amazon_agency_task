package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.StatisticTrafficByDate;

import java.math.BigDecimal;

public record StatisticTrafficByDateDTO(
        Integer browserPageViews,
        Integer browserPageViewsB2B,
        Integer mobileAppPageViews,
        Integer mobileAppPageViewsB2B,
        Integer pageViews,
        Integer pageViewsB2B,
        Integer browserSessions,
        Integer browserSessionsB2B,
        Integer mobileAppSessions,
        Integer mobileAppSessionsB2B,
        Integer sessions,
        Integer sessionsB2B,
        BigDecimal buyBoxPercentage,
        BigDecimal buyBoxPercentageB2B,
        BigDecimal orderItemSessionPercentage,
        BigDecimal orderItemSessionPercentageB2B,
        BigDecimal unitSessionPercentage,
        BigDecimal unitSessionPercentageB2B,
        Integer averageOfferCount,
        Integer averageParentItems,
        Integer feedbackReceived,
        Integer negativeFeedbackReceived,
        BigDecimal receivedNegativeFeedbackRate
) {
    public StatisticTrafficByDateDTO(StatisticTrafficByDate statistic) {
        this(
                statistic.getBrowserPageViews(),
                statistic.getBrowserPageViewsB2B(),
                statistic.getMobileAppPageViews(),
                statistic.getMobileAppPageViewsB2B(),
                statistic.getPageViews(),
                statistic.getPageViewsB2B(),
                statistic.getBrowserSessions(),
                statistic.getBrowserSessionsB2B(),
                statistic.getMobileAppSessions(),
                statistic.getMobileAppSessionsB2B(),
                statistic.getSessions(),
                statistic.getSessionsB2B(),
                statistic.getBuyBoxPercentage(),
                statistic.getBuyBoxPercentageB2B(),
                statistic.getOrderItemSessionPercentage(),
                statistic.getOrderItemSessionPercentageB2B(),
                statistic.getUnitSessionPercentage(),
                statistic.getUnitSessionPercentageB2B(),
                statistic.getAverageOfferCount(),
                statistic.getAverageParentItems(),
                statistic.getFeedbackReceived(),
                statistic.getNegativeFeedbackReceived(),
                statistic.getReceivedNegativeFeedbackRate()
        );
    }
}
