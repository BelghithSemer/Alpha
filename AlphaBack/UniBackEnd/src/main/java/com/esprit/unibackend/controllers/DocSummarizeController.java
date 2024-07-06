package com.esprit.unibackend.controllers;

import com.esprit.unibackend.services.DocumentSummarizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Tag(name = "DocSummarize")
@RequestMapping("/docSum")
public class DocSummarizeController {


    private DocumentSummarizationService summarizationService;

    @PostMapping("/summarize")
    public String summarizeDocument(@RequestParam String docPath) throws IOException {

        return summarizationService.extractTextAndSummarize(docPath);
    }
}
