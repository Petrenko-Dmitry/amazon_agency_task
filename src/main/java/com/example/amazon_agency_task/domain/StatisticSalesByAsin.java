package com.example.amazon_agency_task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticSalesByAsin {
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private AmountWithCurrency orderedProductSales;
    private AmountWithCurrency orderedProductSalesB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
}
