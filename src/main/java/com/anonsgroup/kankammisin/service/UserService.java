package com.anonsgroup.kankammisin.service;

import com.anonsgroup.kankammisin.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
