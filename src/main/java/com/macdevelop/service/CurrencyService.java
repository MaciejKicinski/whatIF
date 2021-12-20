package com.macdevelop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macdevelop.entity.CryptoCurrencyEntity;
import lombok.extern.slf4j.Slf4j;
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

    public String getUrlWithTimeStart(String date) {
        String dateTime = date + "T12:00:00";
        StringBuilder url = new StringBuilder();
        url.append(API_URL);
        url.append("&time_start=").append(dateTime);
        url.append(API_KEY);
        log.debug(url.toString());
        return url.toString();
    }

    public String getHistoricBtcCourse(String date) {
        String url = getUrlWithTimeStart(date);
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream inputStream = openConnection.getInputStream();
            CryptoCurrencyEntity[] coinByTime;
            coinByTime = mapper.readValue(inputStream, CryptoCurrencyEntity[].class);
            result.append(coinByTime[0].getPrice_open());
            return result.toString();
        } catch (Exception exception) {
            log.error(exception.toString());
        }
        result.append("error");
        return result.toString();
    }

    public String calculateProfit(String date, BigDecimal investedMoney) {
        StringBuilder result = new StringBuilder();
        double money = investedMoney.doubleValue();
        double pastPrize = Double.parseDouble(getHistoricBtcCourse(date));
        double latestPrize = Double.parseDouble(API_ENDPOINT_FOR_LATEST_BTC_COURSE);
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
