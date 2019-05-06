package com.anonsgroup.kankammisin.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "sorular")
public class Soru {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int soruId;
    @Getter @Setter
    private String baslik;
    @Getter @Setter
    private String soru;
    @Getter @Setter
    private String cevap1;
    @Getter @Setter
    private String cevap2;
    @Getter @Setter
    private String cevap3;
    @Getter @Setter
    private String cevap4;
    @Getter @Setter
    private String dogruCevap;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Kategori kategori;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private User user;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

    @Override
    public String toString() {
        return "Soru{" +
                "soruId=" + soruId +
                ", baslik='" + baslik + '\'' +
                ", soru='" + soru + '\'' +
                ", cevap1='" + cevap1 + '\'' +
                ", cevap2='" + cevap2 + '\'' +
                ", cevap3='" + cevap3 + '\'' +
                ", cevap4='" + cevap4 + '\'' +
                ", kategori=" + kategori +
                '}';
    }
}
