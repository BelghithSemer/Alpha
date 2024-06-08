package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidacyRepo extends JpaRepository<Candidacy , Integer> {
}
