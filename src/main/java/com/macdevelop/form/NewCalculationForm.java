package com.macdevelop.form;

import java.math.BigDecimal;


public class NewCalculationForm {
    private BigDecimal investedMoney;
    private String date;

    public BigDecimal getInvestedMoney() {
        return investedMoney;
    }

    public void setInvestedMoney(BigDecimal investedMoney) {
        this.investedMoney = investedMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NewCalculationForm{" +
                "investedMoney=" + investedMoney +
                ", date='" + date + '\'' +
                '}';
    }
}
