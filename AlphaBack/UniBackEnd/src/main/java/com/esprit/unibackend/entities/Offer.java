package com.esprit.unibackend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private String company;

    @Enumerated(EnumType.STRING)
    private typeOffre type;

    private int duree;
    private Date date;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private Set<Candidacy> candidacies;


}
