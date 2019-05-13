package com.anonsgroup.kankammisin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long testId;

    @Getter @Setter private String testAdi;

    @ManyToOne
    @JoinColumn
    @Getter @Setter private User kimin;

    @Getter @Setter private String testLinki;


}
