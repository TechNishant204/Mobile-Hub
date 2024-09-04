import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../models/review.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private httpClient:HttpClient) { }


   reviewMobileModel:string;

     apiUrl=backendUrl;


     addReview(reviewObj:Review):Observable<any>{

      return this.httpClient.post(this.apiUrl+"/api/review",reviewObj);
    }

    viewAllReviews():Observable<any>{

        return this.httpClient.get(this.apiUrl+"/api/review");
    }

}
