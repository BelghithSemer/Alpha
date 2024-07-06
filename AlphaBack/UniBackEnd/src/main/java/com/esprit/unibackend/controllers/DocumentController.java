package com.esprit.unibackend.controllers;

import com.esprit.unibackend.entities.Cours;
import com.esprit.unibackend.entities.Document;
import com.esprit.unibackend.entities.User;
import com.esprit.unibackend.payload.request.SignatureRequest;
import com.esprit.unibackend.services.DocumentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@Tag(name = "Document")
@RequestMapping("/document")
public class DocumentController {

    private final DocumentServiceImpl serv;
    private final String imageDirectory = "C:/Users/ASUS/Alpha/Documents";
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
    @PostMapping("/show/cours")
    public List<Document> GetByCours(@RequestBody Cours cours){
        return serv.getByCours(cours);
    }

    @GetMapping("/show")
    public List<Document> getAll(){

        return serv.Retrieve();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }


    @PostMapping(value = "/addDoc", consumes = "multipart/form-data")
    public String addDoc(@ModelAttribute MultipartFile doc ){
        MultipartFile photoFile = doc;
        String photoFileName = UUID.randomUUID().toString() + "-" + photoFile.getOriginalFilename();
        savePostPhoto(photoFile, photoFileName);

        return photoFileName;
    }


    private void savePostPhoto(MultipartFile photoFile, String fileName) {
        try {
            // Path userDirectory = Paths.get("C:\\Users\\asus\\Documents\\GitHub\\tawasolna-backend-app\\tawasalna-auth\\src\\main\\resources\\postPhotos");
            Path userDirectory = Paths.get(System.getProperty("user.home") + "\\Alpha\\Documents");

            if (!Files.exists(userDirectory)) {
                Files.createDirectories(userDirectory);
            }

            Path filePath = userDirectory.resolve(fileName);
            Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save document", e);
        }
    }


    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(imageDirectory).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }



    @PostMapping("/{id}/signature")
    public ResponseEntity<String> saveSignature(@PathVariable int id, @RequestBody Map<String, String> request) {
        String signatureData = request.get("signatureData");

        serv.saveSignature(id,signatureData);

        return ResponseEntity.ok("Signature saved successfully");
    }

}
