package com.anonsgroup.kankammisin.controllers;

import com.anonsgroup.kankammisin.Conventers.StringToUser;
import com.anonsgroup.kankammisin.model.*;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import com.anonsgroup.kankammisin.repositories.TestRepository;
import com.anonsgroup.kankammisin.repositories.UserRepository;
import com.anonsgroup.kankammisin.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DigerController {

    @Autowired
    private SoruRepository soruRepository;

   /* @Autowired
    private KategoriRepository kategoriRepository;*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;


    @Autowired
    private SecurityService securityService;

    @GetMapping({"/", "/anasayfa"})
    public ModelAndView anasayfa(Model model) {
        ModelAndView modelAndView = new ModelAndView("anasayfa");
        modelAndView.addObject(soruRepository.findAll());
        return modelAndView;
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
    public ModelAndView testolustur(Model model,boolean basarili) {
        ModelAndView modelAndView = new ModelAndView("testolustur");
        if(basarili){
            modelAndView.addObject("basarili",true);
        }
        List<Soru> sorular  = soruRepository.findByUser(userRepository.findById(0));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long id = userRepository.findByUsername(username).getId();
        TestOlusturForm testOlusturForm = new TestOlusturForm();

        testOlusturForm.setFormList(sorular);
        modelAndView.addObject("test",new Test());
        modelAndView.addObject("form",testOlusturForm);
        modelAndView.addObject("kisiId", id);
        return modelAndView;
    }

    @PostMapping("/testolustur")
    public String testolusturPost(@ModelAttribute("form") TestOlusturForm form,@ModelAttribute("test") Test test){
        testRepository.save(test);

        String param = ""+test.getTestId()+"+"+test.getKimin().getUsername();
        test.setTestLinki("/test?link="+param);
        testRepository.save(test);
        System.out.println(test.getTestId());
        form.getFormList().forEach(soru -> {
            System.out.println(soru.getSoruId());
            if(soru.getSoruId() != 0) {
                soru.setSoruId(0);
                soru.setTest(test);
                soruRepository.save(soru);
            }
            });
        return "redirect:/testolustur?basarili="+true ;
    }

    @GetMapping("/testlerim")
    public ModelAndView testlerim() {
        ModelAndView modelAndView = new ModelAndView("testlerim");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long id = userRepository.findByUsername(username).getId();
        System.out.println("teslerim" + " " + id);
        modelAndView.addObject("testler",testRepository.findAllByKimin_Id(id));
        return modelAndView;
    }


    @GetMapping("/profil")
    public String profil(){ return "profil"; }

}
