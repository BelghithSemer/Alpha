import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { User } from 'src/app/models/User';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent {
  addform:FormGroup;
  Course: cours = new cours();
  constructor(private fb:FormBuilder, private coursesService:CoursesService, private router:Router){
    this.addform = fb.group({
      title:['',Validators.required],
      description:['',Validators.required],
      planification:['',Validators.required],
      startDate:['',Validators.required],
      finishDate:['',Validators.required],
    })
    this.Course = {
      title:'',
      description:'',
      planification:'',
      documents:[],
      startDate:new Date(),
      finishDate:new Date(),
      owner:undefined,
      searched:0,
      id:0
    }

  }


  onSubmit(){
    this.Course.startDate = this.addform.value.startDate;
    this.Course.finishDate = this.addform.value.finishDate;
    this.Course.title = this.addform.value.title;
    this.Course.description = this.addform.value.description;
    this.Course.planification = this.addform.value.planification;

    console.log(this.Course);
    this.coursesService.addCourse(this.Course).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['/courses']);
    })
  }
}
