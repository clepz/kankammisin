package com.anonsgroup.kankammisin.controllers;

import com.anonsgroup.kankammisin.model.*;
import com.anonsgroup.kankammisin.repositories.IstatistikRepository;
import com.anonsgroup.kankammisin.repositories.SoruRepository;
import com.anonsgroup.kankammisin.repositories.TestRepository;
import com.anonsgroup.kankammisin.repositories.UserRepository;
import com.anonsgroup.kankammisin.service.SecurityService;
import com.anonsgroup.kankammisin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private IstatistikRepository istatistikRepository;


    @Autowired
    private UserService kullaniciService;

    @Autowired
    private SecurityService securityService;

    @GetMapping({"/", "/anasayfa"})
    public ModelAndView anasayfa(Model model) {
        ModelAndView modelAndView = new ModelAndView("anasayfa");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        modelAndView.addObject("istatistik",istatistikRepository.findAllByCozulen(username));
        return modelAndView;
    }

    @GetMapping("ara")
    public ModelAndView istatistikAra(@RequestParam("cozen") String cozen){
        ModelAndView modelAndView = new ModelAndView("anasayfa");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        modelAndView.addObject("istatistik",istatistikRepository.findAllByCozenAndCozulen(cozen,username));
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
        test.setTestLinki("http://localhost:8080/testcoz?link="+param);
        testRepository.save(test);
        form.getFormList().forEach(soru -> {
            if(soru.getSoruId() != 0) {
                soru.setSoruId(0);
                soru.setTest(test);
                soruRepository.save(soru);
            }
            });
        return "redirect:/testlerim" ;
    }

    @GetMapping("/testlerim")
    public ModelAndView testlerim() {
        ModelAndView modelAndView = new ModelAndView("testlerim");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long id = userRepository.findByUsername(username).getId();
        modelAndView.addObject("testler",testRepository.findAllByKimin_Id(id));
        return modelAndView;
    }

    @GetMapping("/testcoz")
    public ModelAndView testCoz(String link,Integer error){
        if(error != null){
            ModelAndView modelAndView = new ModelAndView("hata");
            switch (error){
                case ErrorKodlari.AYNI_KISI_TEST:
                    modelAndView.addObject("hata","Kendi Testini Çözemezsin");
                    break;
                case ErrorKodlari.TEST_BULUNMAMAKTA:
                    modelAndView.addObject("hata","Böyle Bir Test Bulunmamaktadır.");
            }
            return modelAndView;

        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ModelAndView modelAndView = new ModelAndView("testcoz");
        String [] parametreler = link.split(" ");
        if(username.equals(parametreler[1]))
            return new ModelAndView("forward:/testcoz?error=1");
        List<Soru> sorular = soruRepository.findAllByTest_TestId(Long.valueOf(parametreler[0]));
        TestOlusturForm testOlusturForm = new TestOlusturForm();
        testOlusturForm.setFormList(sorular);

        modelAndView.addObject("form", testOlusturForm);
        modelAndView.addObject("test",testRepository.findByTestId(Long.valueOf(parametreler[0])));
        modelAndView.addObject("cozulen",parametreler[1]);


        return modelAndView;

    }
    @PostMapping("/testcozuldu")
    public ModelAndView testCozuldu(@ModelAttribute("form") TestOlusturForm form, @ModelAttribute Test test, @ModelAttribute("cevap") WrapperCevaplar cevap, @ModelAttribute("cozulen") String cozulen){

        int dogruSayisi=0;
        List<Soru> sorular = form.getFormList();
        List<String> cevaplar = cevap.getCevap();

        for(int i = 0 ; i<sorular.size() ; i++ ){
            if(sorular.get(i).getDogruCevap().equals(cevaplar.get(i)))
                dogruSayisi++;
        }
        int yanlisSayisi = sorular.size()-dogruSayisi;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Istatistik istatistik = new Istatistik();
        istatistik.setDogruSayisi(dogruSayisi);
        istatistik.setKankalik(kankalikHesapla(sorular.size(),dogruSayisi));
        istatistik.setYanlisSayisi(yanlisSayisi);
        istatistik.setCozen(username);
        istatistik.setCozulen(cozulen);
        istatistik.setTestAdi(test.getTestAdi());
        istatistikRepository.save(istatistik);

        ModelAndView modelAndView = new ModelAndView("anasayfa");
        modelAndView.addObject("istatistik",istatistikRepository.findAllByCozulen(username));
        return modelAndView;

    }


    @GetMapping("/profil")
    public ModelAndView profil(){
        ModelAndView modelAndView = new ModelAndView("profil");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Long id = userRepository.findByUsername(username).getId();
        modelAndView.addObject("prof",userRepository.findById(id));
        return modelAndView;

    }

    @PostMapping("profil")
    public String profilGuncelle(@ModelAttribute("user") User user){
        User eski = userRepository.findById(user.getId());
        eski.setMail(user.getMail());
        eski.setIsim(user.getIsim());
        eski.setSoyisim(user.getSoyisim());
        userRepository.save(user);
        return "redirect:/profil";
    }


    private String kankalikHesapla(int soruSayisi,int dogruSayisi){
        float yuzdelik = (float) ((float) dogruSayisi / soruSayisi * 100.0);
        if(yuzdelik >= 100)
            return "BFF.";
        if( yuzdelik > 93 )
            return "Sırdaş Kankam.";
        if(yuzdelik > 65)
            return "Kankam";
        if(yuzdelik > 33)
            return "Sıradan Arkadaş.";
        if(yuzdelik > 6)
            return "Aramızdaki Arkadaşlığı Gözden Geçirmeliyiz.";
        else
            return "Bu kim?";
    }
}
