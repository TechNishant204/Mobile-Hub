import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mobile } from '../models/mobile.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  apiUrl=backendUrl+"/api/customer";
  
  constructor(private httpclient:HttpClient) { }

  registerCustomer(customer:any):Observable<any>{
    return this.httpclient.post(this.apiUrl,customer);
  }

  viewCustomerById(customerId:any):Observable<any>{
    return this.httpclient.get(this.apiUrl+"/"+customerId);
  }
  
  getCustomerByUserId():Observable<any>{
    return this.httpclient.get(this.apiUrl+"/user/"+JSON.parse(localStorage.getItem("userData")).userId);
  }
}
  

  
  




  



