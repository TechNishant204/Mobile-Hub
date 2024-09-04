import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { Order } from '../models/order.model';
import { Router } from '@angular/router';
import { Mobile } from '../models/mobile.model';
import { ReviewService } from '../services/review.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  orders:Order[]=[];
  sum:number = 0;

  constructor(private orderService:OrderService,private router:Router,private reviewService:ReviewService) { 
  }
  
  ngOnInit(): void {   
       this.orderService.viewOrdersByCustomerId().subscribe((data)=>{

        this.orders=[];
          data.forEach(element => {

                     
                let orderObject:Order={
                   "orderId":element.orderId,
                   "orderPrice":element.orderPrice,
                   "customer":element.customer,
                   "quantity":element.quantity,
                   "mobiles":element.mobiles,
                   "dateOrdered":element.dateOrdered
                }

                this.orders.push(orderObject);
                console.log(this.orders);
          });
        
       })
  }  
  
  totalPrice():number {
    for(let data of this.orders){
        this.sum+=data.orderPrice;
    }
    return this.sum;
  }
    addReview(mobileModel:string) {
      this.reviewService.reviewMobileModel=mobileModel;
      return this.router.navigate(['addreview']);
    }
  }

