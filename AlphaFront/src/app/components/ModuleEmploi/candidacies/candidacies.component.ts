import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Candidacy } from 'src/app/models/Candidacy';
import { Offer, typeOffre } from 'src/app/models/Offer';

@Component({
  selector: 'app-candidacies',
  templateUrl: './candidacies.component.html',
  styleUrls: ['./candidacies.component.css']
})
export class CandidaciesComponent {
    candidacies:Candidacy[]=[];
    offer !: Offer; 
    id!: number;


    constructor(private os:OfferService, private route:ActivatedRoute,private http:HttpClient){
        
    }

    ngOnInit(){
      this.offer = {
        id: 0,
        description: "string",
        company: "string",
        type: typeOffre.Formation,
        duree: 0,
        date: new Date(),
        candidacies: []
      };


      const idString = this.route.snapshot.paramMap.get('id');
      if (idString !== null) {
        this.id = +idString;
        if (!isNaN(this.id)) {
          this.getOfferById(this.id);
          
        } else {
          console.error('Invalid offer id');
        }
      } else {
        console.error('No offer id provided');
      }
      
      
    }
    getOfferById(id: number): void {
      this.os.getOffer(id).subscribe(
        (data: Offer) => {
          this.offer = data;
          console.log(this.offer);
          this.os.getCandidacies(this.offer).subscribe((data)=>{
            this.candidacies=data;
            console.log(data);
            this.candidacies=this.candidacies;
            /// calculating matching score 
            this.candidacies.forEach((candidacy) => {
              this.calculateScore(candidacy);
            });
          });
        },
        (error) => {
          console.error('Error fetching offer:', error);
        }
      );
    }
    formatDate(timestamp: number): string {
      const date = new Date(timestamp);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      const hours = ('0' + date.getHours()).slice(-2);
      const minutes = ('0' + date.getMinutes()).slice(-2);
      const seconds = ('0' + date.getSeconds()).slice(-2);
      const milliseconds = ('00' + date.getMilliseconds()).slice(-3);
  
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}.${milliseconds}`;
    }

    calculateScore(candidacy: Candidacy): void {
      const newPath = `C:/Users/ASUS/Alpha/CandidacyCv/${candidacy.cv}`;
      console.log(`Calculating score for CV path: ${newPath}`);
      this.http.get<number>(`http://localhost:8089/candidacy/MatchingScore/${this.offer.id}?path=${encodeURIComponent(newPath)}`)
        .subscribe(
          (data) => {
            console.log(`Score matching for ${newPath}:`, data);
            candidacy.score = data;
          },
          (error) => {
            console.error(`Error calculating score for ${newPath}:`, error);
          }
        );
    }
    


}
