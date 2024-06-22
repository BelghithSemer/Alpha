import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Offer, typeOffre } from 'src/app/models/Offer';

@Component({
  selector: 'app-add-offer',
  templateUrl: './add-offer.component.html',
  styleUrls: ['./add-offer.component.css']
})
export class AddOfferComponent {

  addform: FormGroup;
 offer: Offer;
  constructor(private fb:FormBuilder, private os: OfferService, private router:Router){
  this.addform = this.fb.group({
    company: ['', [Validators.required]],
    description: ['', [Validators.required]],
    type: ['', [Validators.required]],
    duree: ['', [Validators.required]],
    date: ['', [Validators.required]],
  });

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

  onSubmit(){
    this.offer = this.addform.value;
    this.os.addOffer(this.offer).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['/offers']);
    })
  }






}
