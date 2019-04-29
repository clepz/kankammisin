package com.anonsgroup.kankammisin.controllers;

import com.anonsgroup.kankammisin.model.Kategori;
import com.anonsgroup.kankammisin.model.Soru;
import com.anonsgroup.kankammisin.repositories.KategoriRepository;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import com.anonsgroup.kankammisin.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DigerController {

    @Autowired
    private SoruRepository soruRepository;

    @Autowired
    private KategoriRepository kategoriRepository;


    @Autowired
    private SecurityService securityService;

    @GetMapping({"/", "/anasayfa"})
    public ModelAndView anasayfa(Model model) {
        ModelAndView modelAndView = new ModelAndView("anasayfa");
        modelAndView.addObject(soruRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/deneme")
    public String getir(){
        List<Kategori> k = kategoriRepository.findAll();
        Soru a = soruRepository.findByKategori_KategoriId(2);
        System.out.println(a.toString());
        return "redirect:/";

    }
    @GetMapping("/soruekle")
    public String soruEkle(){
        return "soruekle";
    }

    @PostMapping("/soruekle")
    public String soruEkleIslem(@ModelAttribute("soru") Soru soru){
        soruRepository.save(soru);
        return "soruekle";
    }

    @GetMapping("/testolustur")
    public String testolustur() { return "testolustur";}

}
