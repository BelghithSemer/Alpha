package com.esprit.unibackend.controllers;
import com.esprit.unibackend.entities.Comment;
import com.esprit.unibackend.services.CommentServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import com.esprit.unibackend.entities.Livre;
import com.esprit.unibackend.services.DropboxService;
import com.esprit.unibackend.services.LivreServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@Tag(name = "Livre")
@RequestMapping("/livre")
public class LivreController {
    private final LivreServiceImpl serv;
    private final CommentServiceImpl commentService;
    private final String imageDirectory = "C:/Users/ASUS/Alpha/Books";

    private final DropboxService dropboxService;

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
    @GetMapping("/getRecomendation")
    public List<Livre> getRecomendation() {
        List<Livre> allBooks = serv.Retrieve();
        List<Livre> recommendation = new ArrayList<>();

        for (Livre book : allBooks) {
            List<Comment> comments = commentService.findByBook(book);
            int rate = 0;

            for (Comment c : comments) {
                rate += c.getRate();
            }

            if (!comments.isEmpty() && (rate / comments.size()) >= 3) {
                recommendation.add(book);
            }
        }

        return recommendation;
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        serv.Delete(id);
    }

    /*@PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<FileMetadata> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Uploading file : "+file.getOriginalFilename());
            FileMetadata metadata = dropboxService.uploadFile("/" + file.getOriginalFilename(), file.getInputStream());
            return new ResponseEntity<>(metadata, HttpStatus.OK);
        } catch (DbxException | IOException e) {

            System.out.println("Error uploading file : "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PostMapping(value = "/uploadtwo", consumes = "multipart/form-data")
    public String handleFileUplad(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) throws Exception {
        dropboxService.uploadFileTwo(file, "/"+file.getOriginalFilename());
        return "You successfully uploaded " + filePath + "!!";
    }

    @GetMapping("/list")
    public List<Map<String, Object>> index(@RequestParam(value = "target", required = false, defaultValue = "") String target) throws Exception {
        return dropboxService.getFileList(target);
    }
    @GetMapping("/download")
    public void downloadFile(@RequestParam("path") String path, HttpServletResponse response) {
        try {
            System.out.println("Downloading file: " + path);
            // Set the content type for the response
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + path + "\"");

            dropboxService.downloadFile(path, response.getOutputStream());
            response.flushBuffer();
        } catch (DbxException | IOException e) {
            System.out.println("Error downloading file"+ e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value = "/addCover", consumes = "multipart/form-data")
    public String addCV(@ModelAttribute MultipartFile cover ){
        MultipartFile photoFile = cover;
        String photoFileName = UUID.randomUUID().toString() + "-" + photoFile.getOriginalFilename();
        savePostPhoto(photoFile, photoFileName);

        return photoFileName;
    }

    private void savePostPhoto(MultipartFile photoFile, String fileName) {
        try {
            Path userDirectory = Paths.get(System.getProperty("user.home") + "\\Alpha\\Books");

            if (!Files.exists(userDirectory)) {
                Files.createDirectories(userDirectory);
            }

            Path filePath = userDirectory.resolve(fileName);
            Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save post photo", e);
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
}
