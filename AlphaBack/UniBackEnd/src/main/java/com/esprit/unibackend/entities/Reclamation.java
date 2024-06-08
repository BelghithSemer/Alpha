package com.esprit.unibackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String description;
    String category;

    @Enumerated(EnumType.STRING)
    StatusRec status;

    @ManyToOne
    private User creator;
}
