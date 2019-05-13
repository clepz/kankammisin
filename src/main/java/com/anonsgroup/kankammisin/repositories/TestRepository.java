package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Long> {

    List<Test> findAllByKimin_Id(Long id);
}
