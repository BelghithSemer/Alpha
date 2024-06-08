package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.CoursRepo;
import com.esprit.unibackend.entities.Cours;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoursServiceImpl implements IService<Cours> {

    private final CoursRepo repo;
    @Override
    public Cours Create(Cours session) {
        return repo.save(session);
    }

    @Override
    public Cours Update(Cours cours) {
        return repo.save(cours);
    }

    @Override
    public Cours Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Cours> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
