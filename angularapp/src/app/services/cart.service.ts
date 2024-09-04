import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../models/cart.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  apiUrl=backendUrl+"/api/cart";
  constructor(private httpclient:HttpClient) { }

  
   addToCart(cartData:Cart):Observable<any>{

        return this.httpclient.post(this.apiUrl,cartData);
   }

 
      getAllMobileFromCart():Observable<any>{
        
        return this.httpclient.get(this.apiUrl+"/"+JSON.parse(localStorage.getItem("cartData")));
      }

    removeMobileFromCart(mobileId:number):Observable<any>{

        return this.httpclient.delete(this.apiUrl+"/"+localStorage.getItem("cartData")+"/mobile/"+mobileId);
    }

    removeAllMobilesFromCartUsingMobileId(mobileId:number):Observable<any>{

         return this.httpclient.delete(this.apiUrl+"/removemobile/"+localStorage.getItem("cartData")+"/"+mobileId);
    }

     getCartByCustomerId():Observable<any>{

          return this.httpclient.get(this.apiUrl+"/customer/"+JSON.parse(localStorage.getItem("customer")).customerId);
     }

     clearMobilesInCartAfterOrder():Observable<any>{

      let newCartObject:Cart=
       {
        "cartId":JSON.parse(localStorage.getItem("cartData")),
        "mobiles":[],
        "customer":JSON.parse(localStorage.getItem("customer")),
        "totalAmount":0
       }
         return this.httpclient.put(this.apiUrl+"/"+localStorage.getItem("cartData"),newCartObject);
     }
    
    updateCart(cartDetails:any):Observable<any>{
      return this.httpclient.put(this.apiUrl+"/"+localStorage.getItem("cartData"),cartDetails);
    }
 
}


  // addCart(cart:any):Observable<any>{
  //   return this.httpclient.post(this.apiUrl,cart);
  // }
  // removeMobileFromCart(cartId:number,mobileId:number){

  // }

