package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Bibliotheque;
import com.esprit.unibackend.services.BibliothequeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Biblio")
@RequestMapping("/biblio")
public class BiblithequeController {

    private final BibliothequeServiceImpl serv;

    @PostMapping("/add")
    public Bibliotheque add(@RequestBody Bibliotheque biblio ){
        return serv.Create(biblio);
    }
    @PutMapping("/update")
    public Bibliotheque Update(@RequestBody Bibliotheque biblio){

        return serv.Update(biblio);
    }

    @GetMapping("/show/{id}")
    public Bibliotheque Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Bibliotheque> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
