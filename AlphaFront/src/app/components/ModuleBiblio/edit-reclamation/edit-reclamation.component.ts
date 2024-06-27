import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReclamationService } from 'src/app/Services/ModuleBiblio/reclamation.service';
import { Reclamation } from 'src/app/models/Reclamation';

@Component({
  selector: 'app-edit-reclamation',
  templateUrl: './edit-reclamation.component.html',
  styleUrls: ['./edit-reclamation.component.css']
})
export class EditReclamationComponent {

  editForm!: FormGroup;
  rec!: Reclamation;
  constructor(private fb:FormBuilder, private route:ActivatedRoute, private rs:ReclamationService,private router:Router){
    this.editForm = this.fb.group({
      description: ['',Validators.required],
      category: ['',Validators.required],
    })

  }

  ngOnInit(){
    this.rs.getById(this.route.snapshot.params['id']).subscribe((data)=>{
      this.rec = data;
      console.log(this.rec);
      this.editForm.patchValue(this.rec);
    })
  }
  onSubmit(){
    this.rec.category = this.editForm.value.category;
    this.rec.description = this.editForm.value.description;
    this.rec.date = new Date;
    this.rs.Update(this.rec).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['/reclamation'])
    })
  }
}
