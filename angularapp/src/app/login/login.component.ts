import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user.model';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { CartService } from '../services/cart.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginForm : FormGroup;
  userDetails:User;
  constructor(private service:AuthService,private builder:FormBuilder,private route:Router,private customerService:CustomerService,private cartService:CartService) { 
    this.loginForm = builder.group({
      username:builder.control("",[Validators.required]),
      password:builder.control("",Validators.required)
    });
  }

  ngOnInit(): void {
  }

  loginCheck(){
   
    let username=this.loginForm.get('username').value;
    let password=this.loginForm.get('password').value;

          this.userDetails=this.loginForm.value;

    this.service.login(this.userDetails).subscribe(data=>{
      localStorage.clear();
       localStorage.setItem("userData",JSON.stringify(data));

          if(data.role=='ADMIN'){
               this.route.navigate(['/addmobile']);
          }
          else if(data.role=='CUSTOMER'){
            this.customerService.getCustomerByUserId().subscribe((data)=>{

                localStorage.setItem("customer",JSON.stringify(data));

                this.cartService.getCartByCustomerId().subscribe((cartResponse)=>{

                           localStorage.setItem("cartData",cartResponse.cartId);
                })
                
                this.route.navigate(['/customer/viewmobiles']);
              
            },
            (error)=>{
              
                   this.route.navigate(['/dashboard']);
            })

              
          }
    },
    (error)=>{
       
            if(error.status==403){

              Swal.fire({text:"Invalid Credentials",icon:"error"});
            }
    }
    )
  }
           

}
