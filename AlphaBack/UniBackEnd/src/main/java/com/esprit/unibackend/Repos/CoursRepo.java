package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepo extends JpaRepository<Cours, Integer> {
}
