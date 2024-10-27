package com.example.amazon_agency_task.entity;

import com.example.amazon_agency_task.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statistics")
public class Statistic {
    @Id
    private String id;
    private ReportTypeAndOptions reportTypeAndOptions;

    private String date;
    private StatisticSalesByDate salesByDate;
    private StatisticTrafficByDate trafficByDate;

    private String parentAsin;
    private String childAsin;
    private String sku;
    private StatisticSalesByAsin salesByAsin;
    private StatisticTrafficByAsin trafficByAsin;
}

