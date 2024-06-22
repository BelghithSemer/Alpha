package com.esprit.unibackend.Repos;


import com.esprit.unibackend.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepo extends JpaRepository<Livre, Integer> {

    @Query("SELECT l FROM Livre l WHERE l.title LIKE %:title%")
    List<Livre> findAllByTitleLike(@Param("title") String title);
}
