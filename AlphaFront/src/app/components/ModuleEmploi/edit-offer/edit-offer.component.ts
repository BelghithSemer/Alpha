import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Offer, typeOffre } from 'src/app/models/Offer';

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.component.html',
  styleUrls: ['./edit-offer.component.css']
})
export class EditOfferComponent {
  updateform: FormGroup;
  offer: Offer;
  id!:number;
   constructor(private fb:FormBuilder, private os: OfferService, private router:Router, private route:ActivatedRoute){
   this.updateform = this.fb.group({
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
        //this.updateform(data);
        //this.updateform.patchValue(this.offer);
        const formattedDate = this.formatDate(this.offer.date.toString());
        this.updateform.patchValue({
          company: this.offer.company,
          description: this.offer.description,
          type: this.offer.type,
          duree: this.offer.duree,
          date: formattedDate
        });
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

   onSubmit(){
     this.offer = this.updateform.value;
     //console.log(this.offer)
     this.os.updateOffer(this.offer).subscribe((data)=>{
       console.log(data);
       //this.router.navigate(['/offers']);
     })
    }
}
