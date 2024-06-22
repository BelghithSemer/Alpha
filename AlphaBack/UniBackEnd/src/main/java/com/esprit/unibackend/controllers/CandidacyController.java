package com.esprit.unibackend.controllers;


import com.esprit.unibackend.Repos.CandidacyRepo;
import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.payload.request.CandidacyRequest;
import com.esprit.unibackend.services.CandidacySeriviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Candidacy")
@RequestMapping("/candidacy")
public class CandidacyController {

    private final CandidacySeriviceImpl serv;

    private  CandidacyRepo repo;
    @PostMapping(value = "/add")
    public Candidacy add(@RequestBody Candidacy candidacy ){
        return  serv.Create(candidacy);
    }
    @PostMapping(value = "/addCV", consumes = "multipart/form-data")
    public String addCV(@ModelAttribute MultipartFile cv ){
        MultipartFile photoFile = cv;
        String photoFileName = UUID.randomUUID().toString() + "-" + photoFile.getOriginalFilename();
        savePostPhoto(photoFile, photoFileName);

        return photoFileName;
    }

    @PostMapping(value = "showcandidacies")
    public List<Candidacy> getCandidacies (@RequestBody Offer offer){
       return repo.getCandidaciesByOffer(offer);
    }



    private void savePostPhoto(MultipartFile photoFile, String fileName) {
        try {
            // Path userDirectory = Paths.get("C:\\Users\\asus\\Documents\\GitHub\\tawasolna-backend-app\\tawasalna-auth\\src\\main\\resources\\postPhotos");
            Path userDirectory = Paths.get(System.getProperty("user.home") + "\\Alpha\\CandidacyCv");

            if (!Files.exists(userDirectory)) {
                Files.createDirectories(userDirectory);
            }

            Path filePath = userDirectory.resolve(fileName);
            Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save post photo", e);
        }
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
