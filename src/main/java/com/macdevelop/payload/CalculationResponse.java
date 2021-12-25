package com.macdevelop.payload;

import lombok.Data;

@Data
public class CalculationResponse {
    private double latestRate;
    private double profit;
    private double historicalRate;

}
