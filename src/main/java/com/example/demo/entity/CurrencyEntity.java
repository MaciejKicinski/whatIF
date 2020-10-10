package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
    private Integer id;
    private String symbol;
    private String name;

    private String price_usd;
    private String percent_change_24h;
    private String percent_change_1h;
    private String percent_change_7d;

    public Integer getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }
}
