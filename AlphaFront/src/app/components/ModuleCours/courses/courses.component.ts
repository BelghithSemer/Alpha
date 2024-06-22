import { Component } from '@angular/core';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {

  Courses : cours[] = [];

  constructor(private cs:CoursesService){}

  ngOnInit(){
    this.cs.getCourses().subscribe((data)=>{
      this.Courses=data;
      console.log(data);
    });
  }
}
