package com.macdevelop.service;

import com.macdevelop.payload.ApicoinRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CoinApiClient {
    private static final String APICOIN_BASE_URL = "https://rest.coinapi.io/v1/";
    private static final String APICOIN_KEY = "00F2E1CD-67A4-48AA-B0E5-95C5628058FF";
    private static final String USER_AGENT = "Spring 5 WebClient";

    private final WebClient webClient;

    @Autowired
    public CoinApiClient() {
        this.webClient = WebClient.builder()
                .baseUrl(APICOIN_BASE_URL)
                .defaultHeader("X-CoinAPI-Key", APICOIN_KEY)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }

    public Mono<ApicoinRequest> getExchangeRate(String assetIdBase, String assetIdQuote) {
        return webClient.get()
                .uri("exchangerate/{assetIdBase}/{assetIdQuote}", assetIdBase, assetIdQuote)
                .retrieve()
                .bodyToMono(ApicoinRequest.class);
    }

}
