package com.example.demo.controller;

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

    @GetMapping("/home")
    public String getHome(Model model) {
        return "home";
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        return "pickDate";
    }

//2016-01-01T00:00:00

    @PostMapping("/historicalTime")
    public String getHistoricalPriceOpen(@RequestParam String date, @RequestParam String investedMoney, Model model) {
        model.addAttribute("prize", currencyService.getCurrencyByTime(date));
        model.addAttribute("profit", currencyService.calculateProfit(date, investedMoney));
        // String response = currencyService.getCurrencyByTime();
        // model.addAttribute("prize", response);
        return "result";
    }
}