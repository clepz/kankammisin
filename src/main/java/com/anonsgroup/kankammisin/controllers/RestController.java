package com.anonsgroup.kankammisin.controllers;

import com.anonsgroup.kankammisin.model.Soru;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/api/deneme")
    public String dene(){
        return "dasdasdas";
    }

}
