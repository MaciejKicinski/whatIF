package com.macdevelop.service;

import com.macdevelop.form.NewCalculationRequestForm;
import com.macdevelop.payload.CalculationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class CurrencyService {

    @Autowired
    CoinApiClient coinApiClient;

    public CalculationResponse buildResponse(NewCalculationRequestForm form) {
        CalculationResponse response = new CalculationResponse();
        double latestRate = coinApiClient.getExchangeRate("BTC", "USD", LocalDateTime.now()).getRate();
        double historicalRate = coinApiClient.getExchangeRate("BTC", "USD", form.getDate()).getRate();
        response.setLatestRate(latestRate);
        response.setHistoricalRate(historicalRate);
        response.setProfit((form.getInvestedMoney() / response.getHistoricalRate()) * response.getLatestRate());
        log.info("latestPrize: " + response.getLatestRate() +
                "\namountOfBitcoin: " + (form.getInvestedMoney() / response.getLatestRate()) +
                "\nvalueOfYourBitcoin: " + ((form.getInvestedMoney() / response.getHistoricalRate()) * response.getLatestRate()) +
                "\nprofit: " + (form.getInvestedMoney() / response.getHistoricalRate()) * response.getLatestRate());
        return response;
    }
}