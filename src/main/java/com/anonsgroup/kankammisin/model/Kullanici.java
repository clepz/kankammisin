package com.anonsgroup.kankammisin.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kullanicilar")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private String kullanici_adi;

    @Getter @Setter private String parola;

    @Getter @Setter private String parolaKontrol;

}
