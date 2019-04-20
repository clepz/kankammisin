package com.anonsgroup.kankammisin.controllers;



import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/login")
    public String anaEkrn(){
        return "giris";
    }
    @GetMapping(value = "/kayitol")
    public String kayitolekranac(){
        return "kayitol";
    }

}
