package com.esprit.unibackend.controllers;


import com.esprit.unibackend.Repos.CandidacyRepo;
import com.esprit.unibackend.Repos.OfferRepo;
import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.payload.request.CandidacyRequest;
import com.esprit.unibackend.payload.request.CandidateStatistics;
import com.esprit.unibackend.payload.request.OfferStatistics;
import com.esprit.unibackend.services.CandidacySeriviceImpl;
import com.esprit.unibackend.services.OfferServiceImpl;
import com.esprit.unibackend.services.ResumeParserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "Candidacy")
@RequestMapping("/candidacy")
public class CandidacyController {

    private final CandidacySeriviceImpl serv;
    private final ResumeParserService resserv;
    private final OfferServiceImpl offerService;
    private  CandidacyRepo repo;
    private OfferRepo offerRepo;


    private JavaMailSender javaMailSender;
    @PostMapping(value = "/add")
    public Candidacy add(@RequestBody Candidacy candidacy ){


        Candidacy savedCandidacy = serv.Create(candidacy);

        // Send confirmation email to candidate
        sendConfirmationEmail(savedCandidacy.getCandidat().getEmail());

        return savedCandidacy;
    }



    private void sendConfirmationEmail(String recipientEmail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Confirmation of Candidacy");
            helper.setText("Dear Candidate,\n\nYour candidacy has been successfully submitted.\n\nBest regards,\nThe Recruitment Team");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
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

    //@GetMapping (value="parseCv")
    public List<String> ParseResume (@RequestParam String path){
        return this.resserv.parseResume(path);
    }

    @GetMapping(value = "MatchingScore/{id}")
    public int MatchingScore(@PathVariable int id,@RequestParam String path){
        Offer  offer =  offerService.Retrieve(id);
        //System.out.println(offer);
        List<String> offerskills  = serv.extractSkillsFromOffer(offer.getDescription());
        List<String> cvskills = ParseResume(path);

        // calculating the score
        int matchingSkillsCount = 0;
        for (String skill : offerskills) {
            if (cvskills.contains(skill)) {
                matchingSkillsCount++;
            }
        }

        int score = (int) (((double) matchingSkillsCount / offerskills.size()) * 100);
        System.out.println("offer skills list : "+offerskills);
        System.out.println(path+" : score = "+matchingSkillsCount + " offer skills count : "+offerskills.size());
        return score;

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



    @GetMapping("/totalApplications")
    public long getTotalApplications() {
        return serv.getTotalApplications();
    }

    @GetMapping("/popularOffers")
    public List<OfferStatistics> getPopularOffers() {
        return serv.getPopularOffers();
    }

    @GetMapping("/candidateStatistics")
    public CandidateStatistics getCandidateStatistics() {
        return serv.getCandidateStatistics();
    }

}
