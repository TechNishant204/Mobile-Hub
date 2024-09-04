import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userRole:String;
  //isAuthenticated:boolean=false;

  apiUrl=backendUrl;

  constructor(private httpClient:HttpClient) { }

  public login(user:User):Observable<any>{
    return this.httpClient.post(this.apiUrl+"/api/login",user);
  }

  public register(user:User):Observable<any>{
    return this.httpClient.post(this.apiUrl+"/api/register",user);
  }

    
    public get isAuthenticated() : boolean {
     
             if(localStorage.getItem("userData")){

                return true;
             }
            else{
              return false;
            }
    }
    

}

