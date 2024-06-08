package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothequeRepo extends JpaRepository<Bibliotheque, Integer> {
}
