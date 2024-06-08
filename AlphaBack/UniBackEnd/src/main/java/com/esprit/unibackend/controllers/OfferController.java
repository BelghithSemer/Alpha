package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.services.OfferServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Offer")
@RequestMapping("/offer")
public class OfferController {

    private final OfferServiceImpl serv;

    @PostMapping("/add")
    public Offer add(@RequestBody Offer document ){
        return serv.Create(document);
    }
    @PutMapping("/update")
    public Offer Update(@RequestBody Offer user){

        return serv.Update(user);
    }

    @GetMapping("/show/{id}")
    public Offer Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Offer> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
