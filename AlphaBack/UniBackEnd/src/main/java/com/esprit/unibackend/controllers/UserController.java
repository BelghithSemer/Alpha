package com.esprit.unibackend.controllers;

import com.esprit.unibackend.entities.User;
import com.esprit.unibackend.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "User")
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl serv;

    @PostMapping("/add")
    public User add(@RequestBody User user ){
        return serv.Create(user);
    }
    @PutMapping("/update")
    public User Update(@RequestBody User user){

        return serv.Update(user);
    }

    @GetMapping("/show/{id}")
    public User Get(@PathVariable int id){

        return serv.Retrieve(id);
    }

    @GetMapping("/show")
    public List<User> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }
}
