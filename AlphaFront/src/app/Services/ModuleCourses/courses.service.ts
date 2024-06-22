import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Document } from 'src/app/models/Document';
import { cours } from 'src/app/models/cours';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private http:HttpClient) { }

  getCourses(){
    return this.http.get<cours[]>('http://localhost:8089/cours/show');
  }

  getCoursById(id:number){
    return this.http.get<cours>('http://localhost:8089/cours/show/'+id);
  }

  addCourse(course:cours){
    return this.http.post<cours>('http://localhost:8089/cours/add',course);
  }
  
  updateCourse(course:cours){
    return this.http.put<cours>('http://localhost:8089/cours/update',course);
  }

  addDocument(document:Document){
    return this.http.post<Document>('http://localhost:8089/document/add',document);
  }

  getDocumentsByCours(course: cours){
    return this.http.post<Document[]>('http://localhost:8089/document/show/cours',course);
  }
}
