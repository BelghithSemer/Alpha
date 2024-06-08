package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Reclamation;
import com.esprit.unibackend.services.ReclamationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Reclamation")
@RequestMapping("/reclamation")
public class ReclamationController {

    private final ReclamationServiceImpl serv;

    @PostMapping("/add")
    public Reclamation add(@RequestBody Reclamation reclamation ){
        return serv.Create(reclamation);
    }
    @PutMapping("/update")
    public Reclamation Update(@RequestBody Reclamation reclamation){

        return serv.Update(reclamation);
    }

    @GetMapping("/show/{id}")
    public Reclamation Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Reclamation> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
