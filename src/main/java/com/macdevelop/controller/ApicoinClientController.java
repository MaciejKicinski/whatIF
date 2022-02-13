package com.macdevelop.controller;

import com.macdevelop.form.NewCalculationRequestForm;
import com.macdevelop.payload.CalculationResponse;
import com.macdevelop.service.CoinApiClient;
import com.macdevelop.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/")
public class ApicoinClientController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("home")
    public ModelAndView getHome() {
        ModelAndView mnw = new ModelAndView("home");
        mnw.addObject("newCalculation", new NewCalculationRequestForm());
        return mnw;
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView mnw = new ModelAndView("home");
        mnw.addObject("newCalculation", new NewCalculationRequestForm());
        return mnw;
    }

    @PostMapping(value = "result", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getHistoricalPriceOpen(@ModelAttribute("newCalculation") NewCalculationRequestForm form, Model model) {
        CalculationResponse calculationResponse = currencyService.buildResponse(form);
        model.addAttribute("prize", String.format("%.2f",calculationResponse.getHistoricalRate()));
        model.addAttribute("profit", calculationResponse.getProfit());
        model.addAttribute("date", form.getDate());
        return "result";
    }
}
