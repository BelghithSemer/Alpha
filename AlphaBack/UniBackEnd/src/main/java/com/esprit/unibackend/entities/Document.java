package com.esprit.unibackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)

    private DocCategory category;

    private String content;
    private Date createdate;
    private Date updatedate;
    private String docpath;


    @Column(length = 100000)
    private String signature;


    @ManyToOne
    private Cours cours;




}
