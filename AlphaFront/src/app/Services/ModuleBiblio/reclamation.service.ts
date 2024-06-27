import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reclamation } from 'src/app/models/Reclamation';

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {

  constructor(private http:HttpClient) { }

  addReclamation(rec: Reclamation){
    return this.http.post('http://localhost:8089/reclamation/add',rec);
  }

  getAll(){
    return this.http.get<Reclamation[]>('http://localhost:8089/reclamation/show');
  }

  getById(id:number){
    return this.http.get<Reclamation>('http://localhost:8089/reclamation/show/' + id);
  }

  Update(rec:Reclamation){
    return this.http.put('http://localhost:8089/reclamation/update',rec);
  }
}
