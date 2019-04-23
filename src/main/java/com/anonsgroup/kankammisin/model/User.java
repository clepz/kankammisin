package com.anonsgroup.kankammisin.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private String username;

    @Getter @Setter private String parola;

    @Getter @Setter private String isim;

    @Getter @Setter private String soyisim;

    @Getter @Setter private String mail;

    @ManyToMany
    @Getter @Setter private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "soruId")
    @Getter @Setter private Set<Soru> sorularim;

}
