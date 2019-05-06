package com.anonsgroup.kankammisin.Conventers;

import com.anonsgroup.kankammisin.model.User;
import com.anonsgroup.kankammisin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Service
public class StringToUser implements Formatter<User> {

    @Autowired
    UserRepository  userRepository;

    @Override
    public User parse(String s, Locale locale) throws ParseException {
        Optional<User> user = userRepository.findById(Long.valueOf(s));
        return user.get();
    }

    @Override
    public String print(User user, Locale locale) {
        return null;
    }
}
