package com.example.amazon_agency_task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticSalesByDate {
    private AmountWithCurrency orderedProductSales;
    private AmountWithCurrency orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private AmountWithCurrency averageSalesPerOrderItem;
    private AmountWithCurrency averageSalesPerOrderItemB2B;
    private BigDecimal averageUnitsPerOrderItem;
    private BigDecimal averageUnitsPerOrderItemB2B;
    private AmountWithCurrency averageSellingPrice;
    private AmountWithCurrency averageSellingPriceB2B;
    private Integer unitsRefunded;
    private BigDecimal refundRate;
    private BigDecimal claimsGranted;
    private AmountWithCurrency claimsAmount;
    private AmountWithCurrency shippedProductSales;
    private Integer unitsShipped;
    private Integer ordersShipped;
}
