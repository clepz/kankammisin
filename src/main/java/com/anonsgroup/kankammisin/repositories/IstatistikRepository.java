package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.Istatistik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IstatistikRepository extends JpaRepository<Istatistik,Long> {

    List<Istatistik> findAllByCozulen(String a);
    List<Istatistik> findAllByCozenAndCozulen(String cozen, String cozulen);

}
