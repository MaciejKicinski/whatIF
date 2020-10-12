package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrencyEntity implements Serializable {
    private Float price_open;

    public Float getPrice_open() {
        return price_open;
    }
}