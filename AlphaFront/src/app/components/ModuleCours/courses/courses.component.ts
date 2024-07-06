import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {

  Courses : cours[] = [];
  searchForm!: FormGroup;
  searchList: cours[] = [];
  constructor(private cs:CoursesService, private fb:FormBuilder){
    this.searchForm = this.fb.group({
      searchQuery: [''],
      sortOrder: ['Newly published']
    });

    this.searchForm.valueChanges.subscribe(values => {
      this.search();
    });
  }

  ngOnInit(){
    this.cs.getCourses().subscribe((data)=>{
      this.Courses = data;
      console.log(data);
      
      this.Courses.sort((a, b) => b.searched - a.searched);
    });
  }

  search(){
    console.log(this.searchForm.value.searchQuery)
    if(this.searchForm.value.searchQuery != ""){
      this.cs.searchByTitle(this.searchForm.value.searchQuery).subscribe((data)=>{
        this.searchList = data;
        console.log(this.searchList)
        if( this.searchList.length != 0 ){
          this.Courses = this.searchList;
        }
      });
    }else{
      this.cs.getCourses().subscribe((data)=>{
        this.Courses=data;
        this.Courses.sort((a, b) => b.searched - a.searched);
        console.log(data);
      });
    }
  }
}
