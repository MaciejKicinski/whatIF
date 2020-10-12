package com.example.demo.controller;

import com.example.demo.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatController {
    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/getCatFact")
    public String getCatFact (Model model){
        String response = catService.getFact();
        model.addAttribute("catFact",response);
        System.out.println(response);
        return "home";
    }
}
