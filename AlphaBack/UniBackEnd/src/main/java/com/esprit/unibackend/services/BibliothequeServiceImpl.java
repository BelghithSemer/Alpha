package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.BibliothequeRepo;
import com.esprit.unibackend.entities.Bibliotheque;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BibliothequeServiceImpl implements  IService<Bibliotheque> {

    private final BibliothequeRepo repo;
    @Override
    public Bibliotheque Create(Bibliotheque session) {
        return repo.save(session);
    }

    @Override
    public Bibliotheque Update(Bibliotheque bibliotheque) {
        return repo.save(bibliotheque);
    }

    @Override
    public Bibliotheque Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Bibliotheque> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
