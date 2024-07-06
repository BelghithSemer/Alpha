package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Comment;
import com.esprit.unibackend.entities.Livre;
import com.esprit.unibackend.services.CommentServiceImpl;
import com.esprit.unibackend.services.LivreServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Comment")
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl serv;
    private final LivreServiceImpl livserv;

    @PostMapping("/add")
    public Comment add(@RequestBody Comment user ){
        return serv.Create(user);
    }
    @PutMapping("/update")
    public Comment Update(@RequestBody Comment user){

        return serv.Update(user);
    }

    @GetMapping("/show/{id}")
    public Comment Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Comment> getAll(){

        return serv.Retrieve();
    }

    @GetMapping("/getByBook/{id}")
    public List<Comment> getByBook(@PathVariable int id){
        Livre book = livserv.Retrieve(id);

        return serv.findByBook(book);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
