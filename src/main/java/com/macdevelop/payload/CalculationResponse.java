package com.macdevelop.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalculationResponse {
    private double latestRate;
    private double profit;
    private double historicalRate;
    private LocalDateTime date;
}
