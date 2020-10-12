package com.example.demo.service;

import com.example.demo.entity.CatEntity;
import com.example.demo.entity.CurrencyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class CatService {
    public String getFact() {
        StringBuilder result = new StringBuilder();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URLConnection openConnection = new URL("https://cat-fact.herokuapp.com/facts/random").openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream is = openConnection.getInputStream();

            CatEntity coin = mapper.readValue(is, CatEntity.class);
            result.append(coin.getText());
            return result.toString();
        }catch (Exception exception){
            System.out.println(exception);
        }
        result.append("error");
        return result.toString();
    }

}
