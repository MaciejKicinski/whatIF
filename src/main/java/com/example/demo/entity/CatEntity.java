package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatEntity {
    private String text;

    public String getText() {
        return text;
    }
}
