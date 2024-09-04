import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../models/order.model';
import { CartService } from './cart.service';
import { Mobile } from '../models/mobile.model';
import { backendUrl } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  apiUrl=backendUrl+"/api/order";

  private totalPrice: number = 0;
  private quantity:number=0;
  private mobilesFromCartByCartId:Mobile[]=[];

  orders:Order[]=[];
  constructor(private httpclient:HttpClient,private cartService:CartService) { }

  // getOrders() {
  //   return this.orders;
  // }

  setTotalPrice(price: number) {
    this.totalPrice = price;
  }

  getTotalPrice(): number {
    return this.totalPrice;
  }

  setmobiles(mobiles: Mobile[]) {
    this.mobilesFromCartByCartId = mobiles;
  }

  getmobiles(): Mobile[] {
    return this.mobilesFromCartByCartId;
  }

  setQuantity(quantity: number) {
    this.quantity = quantity;
  }

  getQuantity(): number {
    return this.quantity;
  }
  
  addOrder():Observable<any>{
   
    let orderDetails:Order=
    {
      "orderPrice":this.getTotalPrice(),
      "quantity": this.getQuantity(),
      "mobiles":this.getmobiles(),
      "customer":JSON.parse(localStorage.getItem("customer")),
      "dateOrdered":new Date()
    }
    console.log('order details',orderDetails);
    return this.httpclient.post(this.apiUrl,orderDetails);
  }

  viewAllOrders():Observable<any>{
    return this.httpclient.get(this.apiUrl);
  }

  viewOrderByUserId(userId:any):Observable<any>{
    return this.httpclient.get(this.apiUrl+"/"+userId);
  }

  viewOrdersByCustomerId():Observable<any>{

       return this.httpclient.get(this.apiUrl+"/"+JSON.parse(localStorage.getItem("customer")).customerId);
  }
  
}
  

