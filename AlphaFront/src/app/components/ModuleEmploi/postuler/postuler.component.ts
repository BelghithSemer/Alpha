import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from 'src/app/Services/ModuleEmploi/offer.service';
import { Candidacy } from 'src/app/models/Candidacy';
import { Offer, typeOffre } from 'src/app/models/Offer';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-postuler',
  templateUrl: './postuler.component.html',
  styleUrls: ['./postuler.component.css']
})
export class PostulerComponent implements OnInit {
  CvForm: FormGroup;
  selectedFile!: File;
  offer!: Offer;
  id!: number;
  user!: User;
  candidacy!: Candidacy;

  constructor(private fb: FormBuilder, private os: OfferService, private route: ActivatedRoute,private router:Router) {
    this.CvForm = fb.group({
      cv: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.user = {
      id: 2,
      name: "Belghith Semer",
      email: "semer@gmail.com",
      password: "string",
      phone: 28194715,
      adress: "string",
      role: "student",
      studentClass: "string"
    };
    this.offer = {
      id: 0,
      description: "string",
      company: "string",
      type: typeOffre.Formation,
      duree: 0,
      date: new Date(),
      candidacies: []
    
    };
    this.candidacy = {
      id: 0,
      candidat: this.user,
      offer: this.offer,
      date: new Date(),
      cv: "string"
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

  onFileSelected(event: any) {
    const file = event.target.files[0];
    console.log(file);
    if (file) {
      this.selectedFile = file;
    }
  }

  submit() {
    if (!this.selectedFile) {
      console.error('No file selected');
      return;
    }

    this.os.addCV(this.selectedFile).subscribe(
      (data) => {
        console.log(data);
        this.candidacy.candidat = this.user;
        this.candidacy.cv = data;
        this.candidacy.offer = this.offer;
        this.candidacy.date = new Date();
        console.log(this.candidacy);
        this.os.addCandidacy(this.candidacy).subscribe(
          (data) => {
            console.log(data);
            this.router.navigate(['/offers']);
          },
          (error) => {
            console.error('Error adding candidacy:', error);
          }
        );
      },
      (error) => {
        console.error('Error uploading CV:', error);
      }
    );
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
}
