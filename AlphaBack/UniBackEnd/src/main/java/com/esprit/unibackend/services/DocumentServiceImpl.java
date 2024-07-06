package com.esprit.unibackend.services;


import com.esprit.unibackend.Repos.DocumentRepo;
import com.esprit.unibackend.entities.Cours;
import com.esprit.unibackend.entities.Document;
import edu.stanford.nlp.simple.Sentence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.print.Doc;
import java.io.IOException;
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


    public void saveSignature(int documentId, String signatureData) {
        // Fetch document from repository
        Document document = repo.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        // Update the document with signature data
        document.setSignature(signatureData);

        // Save updated document to repository
        repo.save(document);
    }

    public String getDocumentSignature(int documentId) {
        Document document = repo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + documentId));
        return document.getSignature();
    }




}
