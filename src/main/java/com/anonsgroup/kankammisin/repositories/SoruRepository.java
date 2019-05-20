package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.Soru;
import com.anonsgroup.kankammisin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoruRepository extends JpaRepository<Soru,Integer> {

    List<Soru> findAll();

    List<Soru> findByUser(User user);

    List<Soru> findAllByTest_TestId(Long id);

}
