package com.example.demo.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class NewCalculationForm {
    private BigDecimal investedMoney;
    private String date;
}
