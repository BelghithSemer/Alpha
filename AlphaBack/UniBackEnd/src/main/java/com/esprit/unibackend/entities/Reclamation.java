package com.esprit.unibackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String description;
    private String category;
    private Date date;

    @Enumerated(EnumType.STRING)
    private StatusRec status;

    @ManyToOne
    private User creator;
}
