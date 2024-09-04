import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mobile } from '../models/mobile.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class MobileService {
  apiUrl=backendUrl;

  public editMobileObj:Mobile;
  
  constructor(private httpclient:HttpClient) { }
  
  addMobile(mobile:any):Observable<any>{
    return this.httpclient.post(this.apiUrl+"/api/mobile",mobile);
  }

  viewAllMobile():Observable<any>{
    return this.httpclient.get(this.apiUrl+"/api/mobile");
  }

  updateMobile(mobileId:any,updatedMobile:any):Observable<any>{
    return this.httpclient.put(this.apiUrl+"/api/mobile/"+mobileId,updatedMobile);
  }

  deleteMobile(mobileId:any):Observable<any>{
    return this.httpclient.delete(this.apiUrl+"/api/mobile/"+mobileId);
  }

  getMobileById(mobileId:number):Observable<any>{

       return this.httpclient.get(this.apiUrl+"/api/mobile/"+mobileId);
  }

  // addToCart(mobileId: number) {
  //   return this.httpclient.post(this.apiUrl,mobileId);
  // }
  
}




