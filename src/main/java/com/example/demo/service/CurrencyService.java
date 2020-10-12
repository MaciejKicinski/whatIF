package com.example.demo.service;

import com.example.demo.entity.CryptoCurrencyEntity;
import com.example.demo.entity.CurrencyEntity;
import com.example.demo.entity.RequestParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class CurrencyService {
    //https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/history?period_id=1sec&time_start=2016-01-01T00:00:00&limit=1&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD
    private static final String API_URL = "https://api.coinlore.net/api/ticker/?id=90";
    private static final String API_KEY = "&apikey=6FF8A46F-A517-4F1E-923C-1130A8BEB9FD";
    
    public static RequestParameters getRequestParameters(String date) {
        RequestParameters requestParameters = new RequestParameters(date);
        return requestParameters;
    }
    
    public String getCurrencyByTime(String date) {
        RequestParameters parameters =  CurrencyService.getRequestParameters(date);
        StringBuilder url = new StringBuilder();
        url.append("https://rest.coinapi.io/v1/ohlcv/BITSTAMP_SPOT_BTC_USD/history?");
        url.append("period_id="+parameters.getPeriod_id()+"&");
        url.append("time_start="+parameters.getTime_start()+"&");
        url.append("limit="+parameters.getLimit());
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URLConnection openConnection = new URL(url.append(API_KEY).toString()).openConnection();
            System.out.println(url);
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

}
