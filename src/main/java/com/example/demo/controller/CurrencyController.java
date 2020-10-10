package com.example.demo.controller;

import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/info")
    public String getCurrencyInfo (){
        String response = currencyService.getCurrencyTable();
        System.out.println(response);
        return "home";
    }

    @GetMapping("/pickCurrency")
    public String getResult( Model model) {
        String response = currencyService.getCurrencyTable();
        model.addAttribute("prize", response);
        return "pickCurrency";
    }
}
