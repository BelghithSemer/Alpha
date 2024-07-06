package com.esprit.unibackend.services;


import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import lombok.AllArgsConstructor;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@Service
public class DocumentSummarizationService {

    private final StandardAnalyzer analyzer;

    public DocumentSummarizationService() {
        analyzer = new StandardAnalyzer();
    }

    public String extractTextAndSummarize(String docPath) throws IOException {
        // Read document content
        //String documentText = readDocument(docPath);
        String documentText = extractTextFromPDF(docPath);
        // Use Stanford CoreNLP for summarization
        Document doc = new Document(documentText);
        List<Sentence> sentences = doc.sentences();

        // Summarize using the first few sentences (adjust as needed)
        StringBuilder summary = new StringBuilder();
        int numSentences = Math.min(3, sentences.size()); // Summarize to the first 3 sentences
        for (int i = 0; i < numSentences; i++) {
            summary.append(sentences.get(i).text()).append(" ");
        }

        return summary.toString();
    }

    private String readDocument(String docPath) throws IOException {
        // Read document content from file
        Path path = Paths.get(docPath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    public String extractTextFromPDF(String path) throws IOException {
        try {
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File not found ");
                //log.error("File not found: " + path);
                return null;
            }

            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // Replace untokenizable characters
            text = text.replaceAll("[^\\p{Print}]", " ");
            // Replace FontAwesome icons with their textual equivalents
            //text = replaceFontAwesomeIcons(text);

            document.close();
            return text;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //log.error(e.getMessage());
            return null;
        }
    }
}
