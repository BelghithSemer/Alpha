package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.ReclamationRepo;
import com.esprit.unibackend.entities.Reclamation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReclamationServiceImpl implements IService<Reclamation> {
    private final ReclamationRepo repo;
    @Override
    public Reclamation Create(Reclamation session) {
        return repo.save(session);
    }

    @Override
    public Reclamation Update(Reclamation t) {
        return repo.save(t);
    }

    @Override
    public Reclamation Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Reclamation> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
