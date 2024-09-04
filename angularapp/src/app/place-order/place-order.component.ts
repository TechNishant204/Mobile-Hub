import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {


  totalPrice: number = this.orderService.getTotalPrice();

  customerName:string=JSON.parse(localStorage.getItem("customer")).customerName;
  address:string=JSON.parse(localStorage.getItem("customer")).address;
   

  constructor(private orderService: OrderService,private router:Router,private cartService:CartService) { 

  }

  ngOnInit(): void {
 
  }

  makePayment() {
    Swal.fire({text:"Payment was successfull",icon:"success"});
    
    this.orderService.addOrder().subscribe((data)=>{

      Swal.fire({text:"Order placed successfull",icon:"success"});

          this.cartService.clearMobilesInCartAfterOrder().subscribe((data)=>{

            this.router.navigate(['/myorders']);
          })
    })
      
  }
}
