package com.anonsgroup.kankammisin.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter Long id;

    @Getter @Setter String isim;

    @ManyToMany(mappedBy = "roles")
    @Getter @Setter private Set<User> users;


}
