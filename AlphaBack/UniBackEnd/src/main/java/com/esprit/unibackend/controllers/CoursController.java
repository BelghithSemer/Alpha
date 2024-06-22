package com.esprit.unibackend.controllers;


import com.esprit.unibackend.entities.Cours;
import com.esprit.unibackend.entities.Document;
import com.esprit.unibackend.services.CoursServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Cours")
@RequestMapping("/cours")
public class CoursController {


    private final CoursServiceImpl serv;


    @PostMapping("/add")
    public Cours add(@RequestBody Cours document ){
        return serv.Create(document);
    }
    @PutMapping("/update")
    public Cours Update(@RequestBody Cours user){

        return serv.Update(user);
    }

    @GetMapping("serach/{title}")
    public List<Cours> GetByTitle(@PathVariable String title){
        List<Cours> searched = new ArrayList<>();
        searched = serv.getByTitle(title);

        if(!searched.isEmpty()){
            int count  = searched.get(0).getSearched();
            count++;
            searched.get(0).setSearched(count);
            System.out.println(searched.get(0).getSearched());
            serv.Update(searched.get(0));
        }



        return searched;
    }
    @GetMapping("/show/{id}")
    public Cours Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<Cours> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
