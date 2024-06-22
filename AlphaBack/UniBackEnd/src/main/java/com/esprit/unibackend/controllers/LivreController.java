package com.esprit.unibackend.controllers;

import com.esprit.unibackend.entities.Livre;
import com.esprit.unibackend.services.LivreServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Livre")
@RequestMapping("/livre")
public class LivreController {
    private final LivreServiceImpl serv;

    @PostMapping("/add")
    public Livre add(@RequestBody Livre livre ){
        return serv.Create(livre);
    }
    @PutMapping("/update")
    public Livre Update(@RequestBody Livre livre){

        return serv.Update(livre);
    }

    @GetMapping("search/{title}")
    public List<Livre> GetByTitle(@PathVariable String title){
        return serv.findbyTitle(title);
    }

    @GetMapping("/show/{id}")
    public Livre Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Livre> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
