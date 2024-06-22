import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { DocCategory, Document } from 'src/app/models/Document';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-add-document',
  templateUrl: './add-document.component.html',
  styleUrls: ['./add-document.component.css']
})
export class AddDocumentComponent {
  addform:FormGroup;
  document: Document;
  //doccategory!: DocCategory;
  course:cours;
  constructor(private fb:FormBuilder,private coursesService:CoursesService,private route: ActivatedRoute,private router:Router){
    this.addform = this.fb.group({
      category:['',Validators.required],
      content:['',Validators.required],
    });
    this.document = {
      id:undefined,
      category: DocCategory.ressource,
      content:'',
      cours:undefined,
      createdate:new Date(),
      updatedate:new Date(),
    }
    this.course ={
      id:undefined,
      title:'',
      description:'',
      planification:'',
      startDate:new Date(),
      finishDate:new Date(),
      documents:[],
      searched:0,
      owner: undefined
    };
  }

  ngOnInit(){
    this.coursesService.getCoursById(this.route.snapshot.params['id']).subscribe((data)=>{
      this.course = data;
      console.log(this.course)
      
    })
  }
  onSubmit(){
    this.document.category = this.addform.value.category;
    this.document.content = this.addform.value.content;
    this.document.cours = this.course;
    this.document.createdate = new Date();
    this.document.updatedate = new Date();
    this.document.cours = this.course;
    this.coursesService.addDocument(this.document).subscribe((data)=>{
      console.log(data);
      this.document = data;
     this.router.navigate(['/course-details/'+this.course.id]);
    })

  }
}
