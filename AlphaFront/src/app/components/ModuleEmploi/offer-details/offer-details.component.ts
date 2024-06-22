import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Offer, typeOffre } from 'src/app/models/Offer';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent {
  offer: Offer;
  id!:number;

  constructor(private os:OfferService,private route:ActivatedRoute){
    this.offer = {
      id: undefined,
      company : "",
      type: typeOffre.Stage,
      description :"",
      duree : 0,
      date : new Date,
      candidacies: []
    }
  }
  ngOnInit(): void {
    // Get the id from the route and convert it to a number
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
        
      },
      (error) => {
        console.error('Error fetching offer:', error);
      }
    );
  }
  formatDate(date: string): string {
    // Assuming the date is in a different format, convert it to YYYY-MM-DD
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();
  
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
  
    return [year, month, day].join('-');
  }


}
