package com.macdevelop.controller;

import com.macdevelop.form.NewCalculationForm;
import com.macdevelop.service.CoinApiClient;
import com.macdevelop.service.CurrencyService;
import com.macdevelop.validator.NewCalculationFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/")
public class ApicoinClientRestController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private NewCalculationFormValidator validator;
    @Autowired
    CoinApiClient coinApiClient;

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

    @PostMapping(value = "historicalTime")
    public String getHistoricalPriceOpen(@ModelAttribute("newCalculation") @Validated NewCalculationForm form,
                                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        String date = form.getDate();
        //Fixme
        model.addAttribute("prize", currencyService.getHistoricBtcCourse(date));
        model.addAttribute("profit", currencyService.calculateProfit(date, form.getInvestedMoney()));
        model.addAttribute("date", date);
        return "result";
    }
}
