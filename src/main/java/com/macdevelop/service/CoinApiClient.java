package com.macdevelop.service;

import com.macdevelop.payload.ApicoinRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CoinApiClient {
    private static final String APICOIN_BASE_URL = "https://rest.coinapi.io/v1/";

    @Autowired
    HttpEntity<Void> httpEntity;
    @Autowired
    RestTemplate restTemplate;

    public ApicoinRequest getExchangeRate(String assetIdBase, String assetIdQuote, String date) {
        ResponseEntity<ApicoinRequest> response = restTemplate.exchange(
                APICOIN_BASE_URL + "exchangerate/{assetIdBase}/{assetIdQuote}?time={date}",
                HttpMethod.GET,
                httpEntity,
                ApicoinRequest.class,
                assetIdBase, assetIdQuote, date);
        return response.getBody();
    }
}
