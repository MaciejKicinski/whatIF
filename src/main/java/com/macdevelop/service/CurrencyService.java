package com.macdevelop.service;

import com.macdevelop.payload.CalculationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
@Slf4j
public class CurrencyService {
    private static final String API_URL = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/history?period_id=1sec&limit=1";
    private static final String API_KEY = "&apikey=00F2E1CD-67A4-48AA-B0E5-95C5628058FF";
    // second api key in case when the limit is reached
    // private static final String API_KEY = "&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD";
    private static final String API_ENDPOINT_FOR_LATEST_BTC_COURSE = "https://rest.coinapi.io/v1/ohlcv/BTC/USD/latest?period_id=1sec&limit=1";

    @Autowired
    CoinApiClient coinApiClient;

    private CalculationResponse createResponse(String date) {
        CalculationResponse result = new CalculationResponse();
        result.setLatestRate(coinApiClient.getExchangeRate("BTC", "USD").getRate());
        result.setHistoricalRate(coinApiClient.getExchangeRateAtSpecificTime("BTC", "USD", date).getRate());
        return result;
    }

    public CalculationResponse calculateProfit(String date, BigDecimal investedMoney) {
        CalculationResponse response = createResponse(date);
        double money = investedMoney.doubleValue();
        double pastPrize = response.getHistoricalRate();
        double latestPrize = response.getLatestRate();
        double profit = (money / pastPrize) * latestPrize + money;
        log.info("latestPrize: " + latestPrize + "\n" + "amountOfBitcoin: " + (money / pastPrize) + "\n" + "valueOfYourBitcoin: " + ((money / pastPrize) * latestPrize) + "\n" + "profit: " + profit);
        DecimalFormat df = new DecimalFormat("#.##");
 //       double parsedProfit = Double.parseDouble(df.format(profit));
        response.setProfit(profit);
        return response;
    }
}
