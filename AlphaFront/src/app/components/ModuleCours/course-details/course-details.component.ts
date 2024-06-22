import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { Document } from 'src/app/models/Document';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent {
    cours:cours = new cours();
    id!:number;
    documents:Document[] = [];
    constructor(private cs:CoursesService,private route:ActivatedRoute){
      
    }
    ngOnInit(){
      const idString = this.route.snapshot.paramMap.get('id');
      if (idString !== null) {
        this.id = +idString;
        if (!isNaN(this.id)) {
          this.getCoursById(this.id);
        } else {
          console.error('Invalid offer id');
        }
      } else {
          console.error('No offer id provided');
        }
       
        
        
        
        
    }
    getCoursById(id: number): void {
      this.cs.getCoursById(id).subscribe(
        (data: cours) => {
          this.cours = data;
          console.log(this.cours)
          this.cs.getDocumentsByCours(this.cours).subscribe((data)=>{
            this.documents = data;
            console.log(this.documents);
          })
          //this.documents = this.cours.documents;
        },
        (error) => {
          console.error('Error fetching offer:', error);
        }
      );
    }

}
