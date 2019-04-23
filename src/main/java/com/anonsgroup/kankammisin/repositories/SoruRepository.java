package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.Soru;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoruRepository extends JpaRepository<Soru,Integer> {

    List<Soru> findAll();
    Soru findByKategori_KategoriId(int sayi);

}
