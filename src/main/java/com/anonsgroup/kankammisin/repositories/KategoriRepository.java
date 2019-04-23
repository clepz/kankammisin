package com.anonsgroup.kankammisin.repositories;

import com.anonsgroup.kankammisin.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KategoriRepository extends JpaRepository<Kategori,Integer> {

    List<Kategori> findAll();

}
