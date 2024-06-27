import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReclamationService } from 'src/app/Services/ModuleBiblio/reclamation.service';
import { Reclamation, StatusRec } from 'src/app/models/Reclamation';

@Component({
  selector: 'app-add-reclamation',
  templateUrl: './add-reclamation.component.html',
  styleUrls: ['./add-reclamation.component.css']
})
export class AddReclamationComponent {
  reclamation !: Reclamation;
  addForm!: FormGroup;
  constructor(private fb:FormBuilder,private  rs : ReclamationService){
    this.addForm = this.fb.group({
    
    description: ['',Validators.required],
    category: ['',Validators.required],

    })

    this.reclamation = {
      id:0,
    description:"string",
    date : new Date,
    creator: undefined,
    category :" ",
    status:StatusRec.EnAttente
    }


  }

  

  onSubmit(){
    this.reclamation.category = this.addForm.value.category;
    this.reclamation.description = this.addForm.value.description;
    

    this.rs.addReclamation(this.reclamation).subscribe((data)=>{
      console.log(data);
    })


  }
}
