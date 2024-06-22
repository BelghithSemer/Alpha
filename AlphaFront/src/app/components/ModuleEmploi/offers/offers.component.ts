import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Offer } from 'src/app/models/Offer';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent {

  offers:Offer[] = [];
  
  constructor(private os:OfferService, private fb:FormBuilder){
    
  }
  
  ngOnInit(){
    this.os.getOffers().subscribe((data)=>{
      this.offers = data;
      console.log(this.offers);
    })
  }
}
