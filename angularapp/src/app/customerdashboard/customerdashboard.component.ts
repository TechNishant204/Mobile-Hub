import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/customer.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../services/customer.service';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-customerdashboard',
  templateUrl: './customerdashboard.component.html',
  styleUrls: ['./customerdashboard.component.css']
})
export class CustomerdashboardComponent implements OnInit {

  customerDetails:Customer;
  customerForm:FormGroup;
  constructor(private builder:FormBuilder,private service:CustomerService,
     private authService:AuthService,private route:Router) { 

            this.customerForm=builder.group({

                 customerName:builder.control("",Validators.required),
                 address:builder.control("",Validators.required)
            })
  }

  ngOnInit(): void {
  }

   public onSubmit(){

         this.customerDetails=this.customerForm.value;
         this.customerDetails.user={"userId": JSON.parse(localStorage.getItem("userData")).userId};
         console.log(this.customerDetails);
        this.service.registerCustomer(this.customerDetails).subscribe(data=>{
                      
               localStorage.setItem('customer',JSON.stringify(data));

               this.route.navigate(['/customer/viewmobiles']);
        });
   }

   public get customerName(){

      return this.customerForm.get('customerName');
   }

   public get address(){

      return this.customerForm.get('address');
   }

}
