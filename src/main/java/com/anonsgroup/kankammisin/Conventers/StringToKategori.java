package com.anonsgroup.kankammisin.Conventers;

import com.anonsgroup.kankammisin.model.Kategori;
import com.anonsgroup.kankammisin.repositories.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class StringToKategori implements Formatter<Kategori> {

    @Autowired
    KategoriRepository kategoriRepository;

    @Override
    public Kategori parse(String s, Locale locale) throws ParseException {
        Kategori kategori = kategoriRepository.findByKategoriId(Integer.valueOf(s));
        return kategori;
    }

    @Override
    public String print(Kategori kategori, Locale locale) {
        return null;
    }
}
