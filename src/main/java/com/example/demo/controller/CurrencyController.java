package com.example.demo.controller;

import com.example.demo.form.NewCalculationForm;
import com.example.demo.service.CurrencyService;
import com.example.demo.validator.NewCalculationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CurrencyController {

    private CurrencyService currencyService;
    private NewCalculationFormValidator validator;

    @Autowired
    public CurrencyController(CurrencyService currencyService, NewCalculationFormValidator validator) {
        this.currencyService = currencyService;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("home")
    public ModelAndView getHome() {
        ModelAndView mnw = new ModelAndView("home");
        mnw.addObject("newCalculation", new NewCalculationForm());
        return mnw;
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView mnw = new ModelAndView("home");
        mnw.addObject("newCalculation", new NewCalculationForm());
        return mnw;
    }

//    @PostMapping("/historicalTime")
//    public String getHistoricalPriceOpen(@RequestParam String date, @RequestParam String investedMoney, Model model) {
//        System.out.println(investedMoney);
//        System.out.println(date);
//        model.addAttribute("prize", currencyService.getCurrencyByTime(date));
//        model.addAttribute("profit", currencyService.calculateProfit(date, investedMoney));
//        model.addAttribute("date", date);
//        return "result";
//    }

    @PostMapping(value = "historicalTime")
    public String getHistoricalPriceOpen(@ModelAttribute("newCalculation") @Validated NewCalculationForm form,
                                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/home";
        }
        model.addAttribute("prize", currencyService.getCurrencyByTime(form.getDate()));
        model.addAttribute("profit", currencyService.calculateProfit(form.getDate(), form.getInvestedMoney()));
        model.addAttribute("date", form.getDate());
        return "result";
    }


}