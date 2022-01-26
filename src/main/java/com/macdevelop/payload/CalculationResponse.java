package com.macdevelop.payload;

import lombok.Data;

@Data
public class CalculationResponse {
    private double latestRate;
    private String profit;
    private double historicalRate;

}
