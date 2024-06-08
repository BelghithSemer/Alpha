package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.CandidacyRepo;
import com.esprit.unibackend.entities.Candidacy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidacySeriviceImpl implements IService<Candidacy> {

    private final CandidacyRepo repo;
    @Override
    public Candidacy Create(Candidacy session) {
        return repo.save(session);
    }

    @Override
    public Candidacy Update(Candidacy candidacy) {
        return repo.save(candidacy);
    }

    @Override
    public Candidacy Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Candidacy> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
