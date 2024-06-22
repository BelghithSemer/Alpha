package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidacyRepo extends JpaRepository<Candidacy , Integer> {

    List<Candidacy> getCandidaciesByOffer(Offer offer);
}
