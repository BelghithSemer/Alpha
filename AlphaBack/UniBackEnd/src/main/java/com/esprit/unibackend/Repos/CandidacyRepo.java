package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidacyRepo extends JpaRepository<Candidacy , Integer> {

    List<Candidacy> getCandidaciesByOffer(Offer offer);

    long countByOffer(Offer offer);
    @Query("SELECT DISTINCT c.candidat FROM Candidacy c")
    List<User> findDistinctCandidat();
}
