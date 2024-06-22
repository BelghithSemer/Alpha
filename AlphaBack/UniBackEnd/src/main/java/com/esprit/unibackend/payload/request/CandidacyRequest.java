package com.esprit.unibackend.payload.request;

import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CandidacyRequest {

    private Offer offer;
    private User candidat;
    private MultipartFile cv;
}
