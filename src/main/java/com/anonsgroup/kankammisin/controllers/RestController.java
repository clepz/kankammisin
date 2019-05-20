package com.anonsgroup.kankammisin.controllers;

import com.anonsgroup.kankammisin.model.Soru;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import com.anonsgroup.kankammisin.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    SoruRepository soruRepository;

    @Autowired
    TestRepository testRepository;

    @GetMapping("/api/deneme")
    public String dene(){
        return "dasdasdas";
    }


    @GetMapping("testsil")
    public String testsil(@RequestParam("id") Long id){


        soruRepository.deleteAllByTestTestId(id);
        testRepository.deleteById(id);


        return "basarili";
    }

}
