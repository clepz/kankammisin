package com.anonsgroup.kankammisin.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "kategori")
public class Kategori {

    @Override
    public String toString() {
        return "Kategori{" +
                "kategoriId=" + kategoriId +
                ", kategoriAdi='" + kategoriAdi + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int kategoriId;

    @Getter @Setter private String kategoriAdi;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="soruId")
    @Getter @Setter private Set<Soru> soru;

}
