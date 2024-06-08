package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepo extends JpaRepository<Livre, Integer> {
}
