package com.macdevelop.service;

import com.macdevelop.payload.CalculationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class CurrencyService {
    @Autowired
    CoinApiClient coinApiClient;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    String now = LocalDateTime.now().format(dtf);

    private CalculationResponse createAndPrepareResponse(String date) {
        log.info(now);
        CalculationResponse result = new CalculationResponse();
        result.setLatestRate(coinApiClient.getExchangeRate("BTC", "USD", now).getRate());
        result.setHistoricalRate(coinApiClient.getExchangeRate("BTC", "USD", date + "T12:00:00").getRate());
        return result;
    }

    public CalculationResponse calculateProfit(String date, BigDecimal investedMoney) {
        CalculationResponse response = createAndPrepareResponse(date);
        double money = investedMoney.doubleValue();
        double pastPrize = response.getHistoricalRate();
        double latestPrize = response.getLatestRate();
        double profit = (money / pastPrize) * latestPrize;
        log.info("latestPrize: " + latestPrize + "\n" + "amountOfBitcoin: " + (money / pastPrize) + "\n" + "valueOfYourBitcoin: " + ((money / pastPrize) * latestPrize) + "\n" + "profit: " + profit);
        response.setProfit(String.format("%.2f", profit));
        return response;
    }
}
