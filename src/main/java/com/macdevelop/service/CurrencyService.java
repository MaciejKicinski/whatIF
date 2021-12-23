package com.macdevelop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macdevelop.entity.CryptoCurrencyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

@Service
@Slf4j
public class CurrencyService {
    private static final String API_URL = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/history?period_id=1sec&limit=1";
    private static final String API_KEY = "&apikey=00F2E1CD-67A4-48AA-B0E5-95C5628058FF";
    // second api key in case when the limit is used up
    // private static final String API_KEY = "&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD";
    private static final String API_ENDPOINT_FOR_LATEST_BTC_COURSE = "https://rest.coinapi.io/v1/ohlcv/BTC/USD/latest?period_id=1sec&limit=1";

    @Autowired
    CoinApiClient coinApiClient;

    public String calculateProfit(String date, BigDecimal investedMoney) {
        StringBuilder result = new StringBuilder();
        double money = investedMoney.doubleValue();
        // Fixme hardcoded base and quote id
        double pastPrize = coinApiClient.getExchangeRateAtSpecificTime("BTC","USD",date).getRate();
        double latestPrize = coinApiClient.getExchangeRate("BTC","USD").getRate();
        double profit = (money / pastPrize) * latestPrize + money;
        log.info("latestPrize: " + latestPrize + "\n" + "amountOfBitcoin: " + (money / pastPrize) + "\n" + "valueOfYourBitcoin: " + ((money / pastPrize) * latestPrize) + "\n" + "profit: " + profit);
        DecimalFormat df = new DecimalFormat("#.##");
        double parsedProfit = Double.parseDouble(df.format(profit));
        result.append(parsedProfit);
        try {
            return result.toString();
        } catch (NumberFormatException numberFormatException) {
            log.error(numberFormatException.toString());
        }
        result.append("error");
        return result.toString();
    }
}
