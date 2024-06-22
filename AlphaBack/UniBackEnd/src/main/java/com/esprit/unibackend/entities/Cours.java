package com.esprit.unibackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String planification;
    private Date startDate;
    private Date finishDate;
    private int searched;

    @ManyToOne
    private User owner;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="cours")
    private Set<Document> documents;

}
