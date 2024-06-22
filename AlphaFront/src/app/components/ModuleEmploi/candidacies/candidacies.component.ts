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


    constructor(private os:OfferService, private route:ActivatedRoute){
        
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


}
