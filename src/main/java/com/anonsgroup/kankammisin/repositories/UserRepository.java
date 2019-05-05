package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findById(long id);


}
