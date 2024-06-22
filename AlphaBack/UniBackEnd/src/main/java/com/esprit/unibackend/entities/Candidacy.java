package com.esprit.unibackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidacy {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne
    private Offer offer;

    @ManyToOne
    private User candidat;


    private String cv;




}
