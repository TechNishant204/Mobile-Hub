import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor() { }
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    

      const token=JSON.parse(localStorage.getItem("userData"));

      if(token){

          request=request.clone({
            setHeaders:{
              Authorization:`Bearer ${token.jsonToken}`
            }
          });
      }else{
        console.error("Token or email not found in localStorage");
      }

      return next.handle(request);
  }
}
