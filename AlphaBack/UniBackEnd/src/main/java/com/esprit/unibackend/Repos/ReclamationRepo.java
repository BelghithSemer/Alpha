package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation, Integer> {

}
