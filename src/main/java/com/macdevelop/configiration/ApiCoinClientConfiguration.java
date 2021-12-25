package com.macdevelop.configiration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiCoinClientConfiguration {
    private static final String APICOIN_KEY = "00F2E1CD-67A4-48AA-B0E5-95C5628058FF";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpEntity<Void> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CoinAPI-Key", APICOIN_KEY);
        return new HttpEntity<>(headers);
    }
}
