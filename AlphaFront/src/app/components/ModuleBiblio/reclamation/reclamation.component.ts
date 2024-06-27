import { Component } from '@angular/core';
import { ReclamationService } from 'src/app/Services/ModuleBiblio/reclamation.service';
import { Reclamation } from 'src/app/models/Reclamation';

@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrls: ['./reclamation.component.css']
})
export class ReclamationComponent {
  
  reclamations: Reclamation[] = [] 
  constructor(private rs: ReclamationService){
    

  }

  ngOnInit(){
    this.rs.getAll().subscribe((data)=>{
      this.reclamations = data;
      console.log(this.reclamations);
    })
  }
}
