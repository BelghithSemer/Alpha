package com.esprit.unibackend.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.StringUtils;
import lombok.extern.slf4j.Slf4j;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResumeParserService {

    private StanfordCoreNLP pipeline;
    public ResumeParserService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");
        this.pipeline = new StanfordCoreNLP(props);

    }




    public List<String> parseResume(String path) {
        List<String> parsedEntities = new ArrayList<>();
        try {
            // Extract text from PDF
            String resumeText = extractTextFromPDF(path);

            // Tokenization
            List<String> tokens = tokenize(resumeText);

            // Debugging: Print tokens
            //System.out.println("Tokens: " + tokens);

            // Named Entity Recognition
            List<String> namedEntities = performNER(tokens);

            // Debugging: Print named entities
            //System.out.println("Named Entities: " + namedEntities);

            // Identify skills
            List<String> skills = identifySkills(namedEntities);

            // Debugging: Print identified skills
            //System.out.println("Skills: " + skills);

            // Identify specialities
            List<String> specialities = identifySpecialities(namedEntities);
            // Debugging: Print identified specialities
            //System.out.println("Specialities: " + specialities);

            // Add skills and specialities to parsed entities
            parsedEntities.addAll(skills);
            parsedEntities.addAll(specialities);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        System.out.println("Parsed Entities : "+parsedEntities);
        return parsedEntities;
    }


    public String extractTextFromPDF(String path) throws IOException {
        try {
            File file = new File(path);

            if (!file.exists()) {
                log.error("File not found: " + path);
                return null;
            }

            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // Replace untokenizable characters
            text = text.replaceAll("[^\\p{Print}]", " ");
            // Replace FontAwesome icons with their textual equivalents
            text = replaceFontAwesomeIcons(text);

            document.close();
            return text;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private String replaceFontAwesomeIcons(String text) {
        // Replace FontAwesome icons with textual equivalents
        text = text.replace("\uF095", "Phone"); // Replace FontAwesome phone icon
        text = text.replace("\uF041", "Map Marker"); // Replace FontAwesome map-marker icon
        text = text.replace("\uF233", "LinkedIn"); // Replace FontAwesome linkedin icon
        text = text.replace("\uF165", "GitHub"); // Replace FontAwesome github icon
        text = text.replace("\uF073", "Calendar"); // Replace FontAwesome calendar icon

        // Add more replacements as needed

        return text;
    }


    public List<String> identifySkills(List<String> entities) {

        Set<String> skills = new HashSet<>();
        for (String entity : entities) {
            if (isTechnicalSkill(entity)) {
                skills.add(entity);
            }
        }
        List<String> extractedSkills = new ArrayList<>(skills);
        return extractedSkills;
    }


    public List<String> identifySpecialities(List<String> entities) {
        List<String> specialities = new ArrayList<>();
        for (String entity : entities) {
            if (isSpeciality(entity)) {
                specialities.add(entity);
            }
        }
        return specialities;
    }


    public boolean isTechnicalSkill(String entity) {
        List<String> skills = Arrays.asList(
                "java", "python", "c++", "c#", "javascript", "html", "CSS", "SQL", "PHP", "ruby","C",
                "swift", "kotlin", "typescript", "go", "rust", "matlab", "perl", "assembly",
                "bash", "powershell", "objective-c", "groovy", "scala", "lua", "dart", "haskell",
                "delphi", "cobol", "fortran", "lisp", "prolog", "scheme", "smalltalk", "verilog",
                "vhdl", "labview", "SpringBoot", "flutter", "android", "Angular", "MongoDb","Java",
                "Firebase", "JavaFx", "NodeJs", "React", "Docker", "Kubernetes", "AWS", "Azure",
                "GCP", "TensorFlow", "PyTorch", "Scikit-Learn", "pandas", "NumPy", "Jenkins",
                "CI/CD", "Microservices", "GraphQL", "ElasticSearch", "Kafka", "Redis", "Spark",
                "Hadoop", "Tableau", "PowerBI", "QlikView", "Salesforce", "SAP", "Oracle", "MySQL",
                "PostgreSQL", "SQLite", "MongoDB", "Cassandra", "Neo4j", "Django", "Flask", "FastAPI",
                "SpringBoot", "Hibernate", "Express.js", "Vue.js", "Svelte", "Next.js", "Nuxt.js",
                "Three.js", "WebAssembly", "TypeORM", "Prisma", "Terraform", "Ansible", "Chef", "Puppet","ASP.NET","Symfony"
        );


        return skills.contains(entity);
    }



    public boolean isSpeciality(String entity) {
        List<String> specialities = Arrays.asList("Application Development","Software Developer","Software Engineer","Website Design","Website Developer","Database Administrator","IT Support","Helpdesk","IT Project Manager","IT Consultant","IT Sales Professional","IT Trainer","Network Engineer","Network Administrator","Cyber Security","Data Analyst","Data Scientist","Machine Learning Engineer","AI Engineer","Business Intelligence","Data Mining","Data Warehousing","Data Modelling","Data Visualisation","Big Data","Cloud Computing","DevOps","Embedded Systems","Game Developer","Mobile Developer","Multimedia Programmer","Systems Analyst","Systems Developer","Systems Administrator","Systems Architect","Systems Engineer","Technical Author","Technical Sales","Technical Support","Technical Writer","Telecommunications","Web Developer","Web Designer","Web Content Manager","Web Content Developer","Web Content Editor","Web Content Administrator","Web Content Coordinator","Web Content Producer","Web Content Publisher","Web Content Writer","Web Content Designer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director");

        return specialities.contains(entity);
    }


    public List<String> tokenize(String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<String> tokens = new ArrayList<>();
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                tokens.add(token.word());
            }
        }
        return tokens;
    }


    public List<String> performNER(List<String> tokens) {
        List<String> namedEntities = new ArrayList<>();

        for (String token : tokens) {
            // Check if the token represents a skill or speciality
            if (isTechnicalSkill(token) || isSpeciality(token)) {
                namedEntities.add(token);
            }

        }

        return namedEntities;
    }

}
