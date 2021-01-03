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
    public String getHomeTest() {
        return "homeBS";
    }

    @GetMapping("/")
    public String getAnything() {
        return "homeBS";
    }

    @PostMapping("/historicalTime")
    public String getHistoricalPriceOpen(@RequestParam String date, @RequestParam String investedMoney, Model model) {
        System.out.println(investedMoney);
        System.out.println(date);
        model.addAttribute("prize", currencyService.getCurrencyByTime(date));
        model.addAttribute("profit", currencyService.calculateProfit(date, investedMoney));
        model.addAttribute("date", date);
        return "result";
    }
}