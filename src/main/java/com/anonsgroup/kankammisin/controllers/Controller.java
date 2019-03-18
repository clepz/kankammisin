package com.anonsgroup.kankammisin.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String anaEkrn(){
        return "giris";
    }

    @GetMapping(value = "/kayitol")
    public String kayitolekranac() {
        return "kayitol";
    }

    @GetMapping(value = "/anasayfa")
    public String anasayfaekranac(){
        return "anasayfa";
    }

}
