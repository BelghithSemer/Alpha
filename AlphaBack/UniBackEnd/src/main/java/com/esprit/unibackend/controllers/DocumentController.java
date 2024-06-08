package com.esprit.unibackend.controllers;

import com.esprit.unibackend.entities.Document;
import com.esprit.unibackend.entities.User;
import com.esprit.unibackend.services.DocumentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Document")
@RequestMapping("/document")
public class DocumentController {

    private final DocumentServiceImpl serv;

    @PostMapping("/add")
    public Document add(@RequestBody Document document ){
        return serv.Create(document);
    }
    @PutMapping("/update")
    public Document Update(@RequestBody Document user){

        return serv.Update(user);
    }

    @GetMapping("/show/{id}")
    public Document Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Document> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
