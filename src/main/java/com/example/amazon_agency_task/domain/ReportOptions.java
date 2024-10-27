package com.example.amazon_agency_task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportOptions {
    private String dateGranularity;
    private String asinGranularity;
}
