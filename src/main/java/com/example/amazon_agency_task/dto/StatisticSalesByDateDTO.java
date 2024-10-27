package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.AmountWithCurrency;
import com.example.amazon_agency_task.domain.StatisticSalesByDate;

import java.math.BigDecimal;

public record StatisticSalesByDateDTO(
        AmountWithCurrency orderedProductSales,
        AmountWithCurrency orderedProductSalesB2B,
        Integer unitsOrdered,
        Integer unitsOrderedB2B,
        Integer totalOrderItems,
        Integer totalOrderItemsB2B,
        AmountWithCurrency averageSalesPerOrderItem,
        AmountWithCurrency averageSalesPerOrderItemB2B,
        BigDecimal averageUnitsPerOrderItem,
        BigDecimal averageUnitsPerOrderItemB2B,
        AmountWithCurrency averageSellingPrice,
        AmountWithCurrency averageSellingPriceB2B,
        Integer unitsRefunded,
        BigDecimal refundRate,
        BigDecimal claimsGranted,
        AmountWithCurrency claimsAmount,
        AmountWithCurrency shippedProductSales,
        Integer unitsShipped,
        Integer ordersShipped
) {
    public StatisticSalesByDateDTO(StatisticSalesByDate statistic) {
        this(
                statistic.getOrderedProductSales(),
                statistic.getOrderedProductSalesB2B(),
                statistic.getUnitsOrdered(),
                statistic.getUnitsOrderedB2B(),
                statistic.getTotalOrderItems(),
                statistic.getTotalOrderItemsB2B(),
                statistic.getAverageSalesPerOrderItem(),
                statistic.getAverageSalesPerOrderItemB2B(),
                statistic.getAverageUnitsPerOrderItem(),
                statistic.getAverageUnitsPerOrderItemB2B(),
                statistic.getAverageSellingPrice(),
                statistic.getAverageSellingPriceB2B(),
                statistic.getUnitsRefunded(),
                statistic.getRefundRate(),
                statistic.getClaimsGranted(),
                statistic.getClaimsAmount(),
                statistic.getShippedProductSales(),
                statistic.getUnitsShipped(),
                statistic.getOrdersShipped()
        );
    }
}
