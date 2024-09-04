import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { Order } from '../models/order.model';

@Component({
  selector: 'app-view-orders',
  templateUrl: './view-orders.component.html',
  styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {
  orders:Order[]=[]
  constructor(private orderService:OrderService) {
    
   }

  ngOnInit(): void {

       this.orderService.viewAllOrders().subscribe((data)=>{

                  this.orders=data;
       })
  }

}
