package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.UserRepo;
import com.esprit.unibackend.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  IService<User>{

    private final UserRepo repo;
    @Override
    public User Create(User t) {
        return repo.save(t);
    }

    @Override
    public User Update(User t) {
        return repo.save(t);
    }

    @Override
    public User Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<User> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }
}
