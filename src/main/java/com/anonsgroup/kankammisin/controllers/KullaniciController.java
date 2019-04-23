package com.anonsgroup.kankammisin.controllers;


import com.anonsgroup.kankammisin.model.User;
import com.anonsgroup.kankammisin.service.UserService;
import com.anonsgroup.kankammisin.service.SecurityService;
import com.anonsgroup.kankammisin.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KullaniciController {

    @Autowired
    private UserService kullaniciService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    UserValidator kullaniciValidator;

    @GetMapping("/kayitol")
    public String kayitol(Model model){
        model.addAttribute("kullaniciForm", new User());

        return "kayitol";
    }

    @PostMapping("/kayitol")
    public String kayitol(@ModelAttribute("kullaniciForm") User kullaniciForm, BindingResult bindingResult){
      kullaniciValidator.validate(kullaniciForm,bindingResult);

      if(bindingResult.hasErrors())
          //Error bilgilerini modele ekleyerek gonder html de de th:if deyip varsa içeriğini yazdır.
          return "kayitol";

      kullaniciService.save(kullaniciForm);
      securityService.autoLogin(kullaniciForm.getUsername(),kullaniciForm.getParola());

        System.out.println(kullaniciForm.getUsername());
      return "redirect:/giris";

    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if(error != null){
            model.addAttribute("error", "Kullanıcı Adı veya Parola Yanlış.");
        }
        if(logout != null){
            model.addAttribute("message", "Başarıyla Çıkış Yaptın.");
        }

        return "giris";
    }

}
