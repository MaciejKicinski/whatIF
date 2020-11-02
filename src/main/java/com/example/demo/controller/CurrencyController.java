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

    @GetMapping("/hometest")
    public String getHomeTest(Model model) {
        return "homeTest";
    }

    @GetMapping("/")
    public String getAnything(Model model) {
        return "homeTest";
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        return "homeTest";
    }

    @PostMapping("/historicalTime")
    public String getHistoricalPriceOpen(@RequestParam String date, @RequestParam String investedMoney, Model model) {
        System.out.println(investedMoney);
        System.out.println(date);
        model.addAttribute("prize", currencyService.getCurrencyByTime(date));
        model.addAttribute("profit", currencyService.calculateProfit(date, investedMoney));
        model.addAttribute("date", date);
        // String response = currencyService.getCurrencyByTime();
        // model.addAttribute("prize", response);
        return "result";
    }
}