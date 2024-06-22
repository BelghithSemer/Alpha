import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.css']
})
export class EditCourseComponent {

  Course: cours;
  editform:FormGroup;
  constructor(private fb:FormBuilder, private coursesService:CoursesService, private route:ActivatedRoute,private router:Router){
    this.Course = new cours();
    this.editform = this.fb.group({
      title:['',Validators.required],
      description:['',Validators.required],
      planification:['',Validators.required],
      startDate:['',Validators.required],
      endDate:['',Validators.required],
    })

    this.coursesService.getCoursById(this.route.snapshot.params['id']).subscribe((data)=>{
      this.Course = data;
      this.editform.patchValue(this.Course);
    })
  }

  onSubmit(){
    this.Course.id = this.route.snapshot.params['id'];
    this.Course.description = this.editform.value.description;
    this.Course.planification = this.editform.value.planification;
    this.Course.startDate = this.editform.value.startDate;
    this.Course.finishDate = this.editform.value.endDate;
    this.Course.title = this.editform.value.title;

    this.coursesService.updateCourse(this.Course).subscribe((data)=>{
      console.log(data);
      this.router.navigate(['/courses']);
    })
  }
}
