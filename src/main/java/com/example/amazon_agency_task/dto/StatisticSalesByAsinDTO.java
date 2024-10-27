package com.example.amazon_agency_task.dto;

import com.example.amazon_agency_task.domain.AmountWithCurrency;
import com.example.amazon_agency_task.domain.StatisticSalesByAsin;

public record StatisticSalesByAsinDTO(
        Integer unitsOrdered,
        Integer unitsOrderedB2B,
        AmountWithCurrency orderedProductSales,
        AmountWithCurrency orderedProductSalesB2B,
        Integer totalOrderItems,
        Integer totalOrderItemsB2B
) {
    public StatisticSalesByAsinDTO(StatisticSalesByAsin statistic) {
        this(
                statistic.getUnitsOrdered(),
                statistic.getUnitsOrderedB2B(),
                statistic.getOrderedProductSales(),
                statistic.getOrderedProductSalesB2B(),
                statistic.getTotalOrderItems(),
                statistic.getTotalOrderItemsB2B()
        );
    }
}
