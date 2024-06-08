package com.esprit.unibackend.services;


import com.esprit.unibackend.Repos.OfferRepo;
import com.esprit.unibackend.entities.Offer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements  IService<Offer> {

    private final OfferRepo repo;
    @Override
    public Offer Create(Offer session) {
        return repo.save(session);
    }

    @Override
    public Offer Update(Offer offer) {
        return repo.save(offer);
    }

    @Override
    public Offer Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Offer> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
