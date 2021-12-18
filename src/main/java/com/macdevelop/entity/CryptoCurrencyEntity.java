package com.macdevelop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrencyEntity {
    private Float price_open;

    public Float getPrice_open() {
        return price_open;
    }
}