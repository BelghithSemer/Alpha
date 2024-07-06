import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offer } from 'src/app/models/Offer';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Candidacy } from 'src/app/models/Candidacy';
@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http:HttpClient) { }

  getOffers(){
    return this.http.get<Offer[]>('http://localhost:8089/offer/show');
  }
  addOffer(offer: Offer){
    return this.http.post('http://localhost:8089/offer/add',offer);
  }
  getOffer(id:number){
    return this.http.get<Offer>(`http://localhost:8089/offer/show/${id}`)
  }
  updateOffer(offer:Offer){
    return this.http.put('http://localhost:8089/offer/update',offer);
  }

  addCV(file:File){
    const formData: FormData = new FormData();
    formData.append('cv', file, file.name);

    return this.http.post(`http://localhost:8089/candidacy/addCV`, formData,{ responseType: 'text' });
  }

  addCandidacy(candidacy:Candidacy){
    return this.http.post('http://localhost:8089/candidacy/add',candidacy);
  }

  getCandidacies(offer:Offer){
    return this.http.post<Candidacy[]>('http://localhost:8089/candidacy/showcandidacies',offer);
  }

  /// Stats Componenet 

  getTotalApplications()  {
    return this.http.get<number>('http://localhost:8089/candidacy/totalApplications');
  }

  getPopularOffers(){
    return this.http.get<any>('http://localhost:8089/candidacy/popularOffers');
  }

  getCandidateStatistics() {
    return this.http.get<any>('http://localhost:8089/candidacy/candidateStatistics');
  }

}
