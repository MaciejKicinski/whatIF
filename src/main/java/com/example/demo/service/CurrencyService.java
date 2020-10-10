package com.example.demo.service;

import com.example.demo.entity.CurrencyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class CurrencyService {

    public String getCurrencyTable() {
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URLConnection openConnection = new URL("https://api.coinlore.net/api/ticker/?id=90").openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream is = openConnection.getInputStream();

            CurrencyEntity[] coin = mapper.readValue(is, CurrencyEntity[].class);
            result.append(coin[0].getPrice_usd());
            return result.toString();
        }catch (Exception exception){
            System.out.println(exception);
        }
        result.append("error");
        return result.toString();
    }
}
