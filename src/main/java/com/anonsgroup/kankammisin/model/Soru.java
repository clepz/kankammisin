package com.anonsgroup.kankammisin.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sorular")
public class Soru {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
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

}
