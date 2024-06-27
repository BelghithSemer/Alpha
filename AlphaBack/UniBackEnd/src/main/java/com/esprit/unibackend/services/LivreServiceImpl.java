package com.esprit.unibackend.services;


import com.esprit.unibackend.Repos.LivreRepo;
import com.esprit.unibackend.entities.Livre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivreServiceImpl implements IService<Livre>{

    private final LivreRepo repo;


    @Override
    public Livre Create(Livre session) {
        return repo.save(session);
    }

    @Override
    public Livre Update(Livre livre) {
        return repo.save(livre);
    }

    @Override
    public Livre Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Livre> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }

    public List<Livre> findbyTitle(String title){
        return repo.findAllByTitleLike(title);
    }





}
