package com.anonsgroup.kankammisin.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String anaEkrn(Model model){
        model.addAttribute("deneme","Hello World!!");
        return "home";
    }

}
