package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepo extends JpaRepository<Cours, Integer> {

    @Query("SELECT c FROM Cours c WHERE c.title LIKE %:title%")
    List<Cours> findAllByTitleLike(@Param("title") String title);

    //List<Cours> findAllByTitle (String title);
}
