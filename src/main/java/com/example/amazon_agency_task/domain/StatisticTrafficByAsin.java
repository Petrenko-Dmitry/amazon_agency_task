package com.example.amazon_agency_task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticTrafficByAsin {
    private Integer browserSessions;
    private Integer browserSessionsB2B;
    private Integer mobileAppSessions;
    private Integer mobileAppSessionsB2B;
    private Integer sessions;
    private Integer sessionsB2B;
    private BigDecimal browserSessionPercentage;
    private BigDecimal browserSessionPercentageB2B;
    private BigDecimal mobileAppSessionPercentage;
    private BigDecimal mobileAppSessionPercentageB2B;
    private BigDecimal sessionPercentage;
    private BigDecimal sessionPercentageB2B;
    private Integer browserPageViews;
    private Integer browserPageViewsB2B;
    private Integer mobileAppPageViews;
    private Integer mobileAppPageViewsB2B;
    private Integer pageViews;
    private Integer pageViewsB2B;
    private BigDecimal browserPageViewsPercentage;
    private BigDecimal browserPageViewsPercentageB2B;
    private BigDecimal mobileAppPageViewsPercentage;
    private BigDecimal mobileAppPageViewsPercentageB2B;
    private BigDecimal pageViewsPercentage;
    private BigDecimal pageViewsPercentageB2B;
    private BigDecimal buyBoxPercentage;
    private BigDecimal buyBoxPercentageB2B;
    private BigDecimal unitSessionPercentage;
    private BigDecimal unitSessionPercentageB2B;
}
