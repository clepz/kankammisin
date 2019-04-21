package com.anonsgroup.kankammisin.validator;

import com.anonsgroup.kankammisin.model.User;
import com.anonsgroup.kankammisin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService kullaniciService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User kullanici = (User) o ;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "NotEmpty");
        if (kullanici.getUsername().length() < 4 || kullanici.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (kullaniciService.findByUsername(kullanici.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parola", "NotEmpty");
        if (kullanici.getParola().length() < 8 || kullanici.getParola().length() > 32) {
            errors.rejectValue("parola", "Size.userForm.password");
        }

    }
}
