package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.services.CandidacySeriviceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Candidacy")
@RequestMapping("/candidacy")
public class CandidacyController {

    private final CandidacySeriviceImpl serv;

    @PostMapping("/add")
    public Candidacy add(@RequestBody Candidacy candidacy ){
        return serv.Create(candidacy);
    }
    @PutMapping("/update")
    public Candidacy Update(@RequestBody Candidacy candidacy){

        return serv.Update(candidacy);
    }

    @GetMapping("/show/{id}")
    public Candidacy Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Candidacy> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }


}
