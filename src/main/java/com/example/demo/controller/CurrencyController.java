package com.example.demo.controller;

import com.example.demo.entity.RequestParameters;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/info")
    public String getCurrencyInfo (Model model){
        String response = currencyService.getCurrencyByTime("1");
        model.addAttribute("prize", response);
        return "pickCurrency";
    }

    @GetMapping("/pickCurrency")
    public String getResult() {
        return "pickCurrency";
    }
//2016-01-01T00:00:00
    @PostMapping ("/historicalTime")
    public String getHistoricalPriceOpen(@RequestParam String date, Model model) {
        model.addAttribute("prize", currencyService.getCurrencyByTime(date));
       // String response = currencyService.getCurrencyByTime();
       // model.addAttribute("prize", response);
        return "pickCurrency";
    }
}