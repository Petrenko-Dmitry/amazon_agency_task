package com.example.amazon_agency_task.domain;

import java.math.BigDecimal;

public record AmountWithCurrency(
        BigDecimal amount,
        String currencyCode
) {
}
