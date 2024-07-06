package com.esprit.unibackend.Repos;


import com.esprit.unibackend.entities.Comment;
import com.esprit.unibackend.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {


    List<Comment>  findAllByBook(Livre book);
 }
