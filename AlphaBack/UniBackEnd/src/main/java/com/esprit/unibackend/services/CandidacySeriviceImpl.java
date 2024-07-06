package com.esprit.unibackend.services;

import com.esprit.unibackend.Repos.CandidacyRepo;
import com.esprit.unibackend.Repos.OfferRepo;
import com.esprit.unibackend.entities.Candidacy;
import com.esprit.unibackend.entities.Offer;
import com.esprit.unibackend.payload.request.CandidateStatistics;
import com.esprit.unibackend.payload.request.OfferStatistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CandidacySeriviceImpl implements IService<Candidacy> {

    private final CandidacyRepo repo;
    private final OfferRepo offerRepository;
    @Override
    public Candidacy Create(Candidacy session) {
        return repo.save(session);
    }

    @Override
    public Candidacy Update(Candidacy candidacy) {
        return repo.save(candidacy);
    }

    @Override
    public Candidacy Retrieve(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Candidacy> Retrieve() {
        return repo.findAll();
    }

    @Override
    public void Delete(int id) {
        repo.deleteById(id);
    }

    public static List<String> extractSkillsFromOffer(String description) {
        List<String> SKILLS = Arrays.asList(
                "Java", "python", "c++", "c#", "javascript", "HTML", "CSS", "SQL",
                "PHP", "ruby", "swift", "kotlin", "typescript", "go", "rust",
                "matlab", "perl", "assembly", "bash", "powershell", "objective-c",
                "groovy", "scala", "lua", "dart", "haskell", "delphi", "cobol",
                "fortran", "lisp", "prolog", "scheme", "smalltalk", "verilog",
                "vhdl", "labview", "springBoot", "flutter", "android", "Angular",
                "MongoDb", "Firebase", "JavaFx", "NodeJs", "React", "Docker",
                "Kubernetes", "AWS", "Azure", "GCP", "TensorFlow", "PyTorch",
                "Scikit-Learn", "pandas", "NumPy", "Jenkins", "CI/CD", "Microservices",
                "GraphQL", "ElasticSearch", "Kafka", "Redis", "Spark", "Hadoop",
                "Tableau", "PowerBI", "QlikView", "Salesforce", "SAP", "Oracle",
                "MySQL", "PostgreSQL", "SQLite", "Cassandra", "Neo4j", "Django",
                "Flask", "FastAPI", "Spring", "Hibernate", "Express.js", "Vue.js",
                "Svelte", "Next.js", "Nuxt.js", "Three.js", "WebAssembly", "TypeORM",
                "Prisma", "Terraform", "Ansible", "Chef", "Puppet","ASP.NET"
        );
        List<String> specialities = Arrays.asList("Application Development","Software Developer",
                "Software Engineer","Website Design","Website Developer","Database Administrator",
                "IT Support","Helpdesk","IT Project Manager","IT Consultant","IT Sales Professional",
                "IT Trainer","Network Engineer","Network Administrator","Cyber Security","Data Analyst",
                "Data Scientist","Machine Learning Engineer","AI Engineer","Business Intelligence","Data Mining",
                "Data Warehousing","Data Modelling","Data Visualisation","Big Data","Cloud Computing","DevOps",
                "Embedded Systems","Game Developer","Mobile Developer","Multimedia Programmer","Systems Analyst",
                "Systems Developer","Systems Administrator","Systems Architect","Systems Engineer","Technical Author",
                "Technical Sales","Technical Support","Technical Writer","Telecommunications","Web Developer","Web Designer",
                "Web Content Manager","Web Content Developer","Web Content Editor","Web Content Administrator","Web Content Coordinator",
                "Web Content Producer","Web Content Publisher","Web Content Writer","Web Content Designer","Web Content Analyst",
                "Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager",
                "Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator",
                "Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer",
                "Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor",
                "Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer","Web Content Administrator",
                "Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer","Web Content Designer",
                "Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist",
                "Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive","Web Content Officer",
                "Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer","Web Content Developer",
                "Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant",
                "Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director","Web Content Executive",
                "Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher","Web Content Producer",
                "Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist",
                "Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director",
                "Web Content Executive","Web Content Officer","Web Content Administrator","Web Content Coordinator","Web Content Publisher",
                "Web Content Producer","Web Content Developer","Web Content Designer","Web Content Writer","Web Content Analyst","Web Content Strategist","Web Content Consultant","Web Content Specialist","Web Content Supervisor","Web Content Manager","Web Content Director");


        Set<String> extractedSkillsSet = new HashSet<>();

        for (String skill : SKILLS) {
            if (description.contains(skill.toLowerCase())) {
                extractedSkillsSet.add(skill);
            }
        }

        for( String spec : specialities){
            if (description.contains(spec)){
                extractedSkillsSet.add(spec);
            }
        }


        List<String> extractedSkills = new ArrayList<>(extractedSkillsSet);
        return extractedSkills;
    }


    public long getTotalApplications() {
        return repo.count();
    }
    public List<OfferStatistics> getPopularOffers() {
        List<Offer> offers = offerRepository.findAll();
        List<OfferStatistics> offerStats = new ArrayList<>();

        for (Offer offer : offers) {
            long applicationCount = repo.countByOffer(offer);
            offerStats.add(new OfferStatistics(offer.getId(), offer.getCompany(), offer.getDescription(), applicationCount));
        }

        offerStats.sort((o1, o2) -> Long.compare(o2.getApplicationCount(), o1.getApplicationCount()));
        return offerStats;
    }

    public CandidateStatistics getCandidateStatistics() {
        long totalCandidates = repo.findDistinctCandidat().size();
        // Add more statistics as needed

        return new CandidateStatistics(totalCandidates);
    }
}
