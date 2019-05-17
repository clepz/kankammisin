package com.anonsgroup.kankammisin.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Istatistik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long istatistikId;

    @Getter
    @Setter
    private String testAdi;

    @Getter
    @Setter
    private String cozen;

    @Getter
    @Setter
    private String cozulen;

    @Getter
    @Setter
    private long dogruSayisi;

    @Getter
    @Setter
    private long yanlisSayisi;

    @Getter
    @Setter
    private String kankalik;


}
