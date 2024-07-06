package com.esprit.unibackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name ;
    private String email;
    private String password;

    private int phone;
    private String adress;
    private String StudentClass;

    private String role;


    @JsonIgnore
    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private Set<Candidacy> candidacies;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments;


    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<Cours> courses;


    @JsonIgnore
    @OneToMany(mappedBy = "creator")
    private Set<Reclamation> reclamations;
}
