import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private routers:Router){}
  
    canActivate(route: ActivatedRouteSnapshot ): boolean{

      let role= JSON.parse(localStorage.getItem("userData")).role;

      let currentUrl=this.routers.getCurrentNavigation().extractedUrl.toString();

      let adminURLs:string[]=['/addmobile','/editmobile','/login','/viewmobiles','/vieworders','/viewreviews','/error',,'/login'];

      let customerURLs:string[]=['/addreview','/customer/viewmobiles','/error','/mycart','/myorders','/placeorder','/dashboard','/viewreviews'];

      if(!localStorage.getItem('userData')){
         
          this.routers.navigate([""]);
          return false;
      }

      else{

            if(role=="ADMIN" && adminURLs.includes(currentUrl)){

               return true;
            }

            else if(role=="CUSTOMER" && customerURLs.includes(currentUrl)){

                return true;
            }
            else{

                 this.routers.navigate([""]);
                 return false;
            }
      }
      
    }
  
}
