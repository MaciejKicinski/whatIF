package com.example.demo.service;

import com.example.demo.entity.CryptoCurrencyEntity;
import com.example.demo.entity.RequestParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

@Service
public class CurrencyService {
    //https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/history?period_id=1sec&time_start=2016-01-01T00:00:00&limit=1&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD
    private static final String API_URL = "https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD";
      private static final String API_KEY = "&apikey=00F2E1CD-67A4-48AA-B0E5-95C5628058FF";
   // private static final String API_KEY = "&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD";

    // https://rest.coinapi.io/v1/ohlcv/BTC/USD/latest?period_id=1MIN&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD
    public static RequestParameters getRequestParameters(String date) {
        String dateTime = date + "T12:00:00";
        RequestParameters requestParameters = new RequestParameters(); //period_id and limit are mocked
        requestParameters.setTime_start(dateTime);
        return requestParameters;
    }

    public String getCurrency() {
        StringBuilder url = new StringBuilder();
        url.append("https://rest.coinapi.io/v1/ohlcv/BTC/USD");
        url.append("/latest?");
        url.append("period_id=1sec");
        url.append("&limit=1");
        url.append(API_KEY);
        return getJson(url);
    }

    public String getCurrencyByTime(String date) {
        RequestParameters parameters = CurrencyService.getRequestParameters(date);
        StringBuilder url = new StringBuilder();
        url.append(API_URL);
        url.append("/history?");
        url.append("period_id=" + parameters.getPeriod_id() + "&");
        url.append("time_start=" + parameters.getTime_start() + "&");
        url.append("limit=" + parameters.getLimit());
        url.append(API_KEY);
        System.out.println(url);
        return getJson(url);
    }

    private String getJson(StringBuilder url) {
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URLConnection openConnection = new URL(url.toString()).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream inputStream = openConnection.getInputStream();
            CryptoCurrencyEntity[] coinByTime;
            coinByTime = mapper.readValue(inputStream, CryptoCurrencyEntity[].class);
            result.append(coinByTime[0].getPrice_open());
            return result.toString();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        result.append("error");
        return result.toString();
    }

    public String calculateProfit(String date, BigDecimal investedMoney) {
        double money = investedMoney.doubleValue();
        double pastPrize = Double.parseDouble(getCurrencyByTime(date));
        StringBuilder result = new StringBuilder();
        try {
            double latestPrize = Double.parseDouble(getCurrency());
            System.out.println("latestPrize: " + latestPrize);
            double amountOfBitcoin = money / pastPrize;
            System.out.println("amountOfBitcoin: " + amountOfBitcoin);
            double valueOfYourBitcoin = amountOfBitcoin * latestPrize;
            System.out.println("valueOfYourBitcoin: " + valueOfYourBitcoin);
            double profit = valueOfYourBitcoin + money;
            System.out.println("profit: " + profit);
            DecimalFormat df = new DecimalFormat("#.##");
            double parsedProfit = Double.parseDouble(df.format(profit));
            result.append(parsedProfit);
            return result.toString();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException);
        }
        result.append("error");
        return result.toString();

    }

}
