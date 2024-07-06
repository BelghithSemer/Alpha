package com.esprit.unibackend.services;


import com.esprit.unibackend.Repos.CommentRepo;
import com.esprit.unibackend.entities.Comment;
import com.esprit.unibackend.entities.Livre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements IService<Comment>{

    private final CommentRepo repo;

    @Override
    public Comment Create(Comment session) {
        return repo.save(session);
    }

    @Override
    public Comment Update(Comment comment) {
        return repo.save(comment);
    }

    @Override
    public Comment Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Comment> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }

    public List<Comment> findByBook(Livre book){
        return repo.findAllByBook(book);
    }
}
