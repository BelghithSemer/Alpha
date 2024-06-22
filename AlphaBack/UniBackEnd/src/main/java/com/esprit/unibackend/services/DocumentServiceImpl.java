package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.DocumentRepo;
import com.esprit.unibackend.entities.Cours;
import com.esprit.unibackend.entities.Document;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements IService<Document> {

    private final DocumentRepo repo;
    @Override
    public Document Create(Document session) {
        return repo.save(session);
    }

    @Override
    public Document Update(Document document) {
        return repo.save(document);
    }

    @Override
    public Document Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Document> Retrieve() {
        return repo.findAll();
    }

    public List<Document> getByCours(Cours cours){
        return repo.findDocumentsByCours(cours);
    }
    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
