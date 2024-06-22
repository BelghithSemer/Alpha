package com.esprit.unibackend.Repos;

import com.esprit.unibackend.entities.Cours;
import com.esprit.unibackend.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document , Integer> {

    List<Document> findDocumentsByCours(Cours cours);
}
