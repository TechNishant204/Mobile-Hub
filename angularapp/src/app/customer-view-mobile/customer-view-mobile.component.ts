import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../services/customer.service';
import { MobileService } from '../services/mobile.service';
import { Mobile } from '../models/mobile.model';
import { CartService } from '../services/cart.service';
import { Cart } from '../models/cart.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customer-view-mobile',
  templateUrl: './customer-view-mobile.component.html',
  styleUrls: ['./customer-view-mobile.component.css']
})
export class CustomerViewMobileComponent implements OnInit {
  
  mobiles:Mobile[] = [];

  constructor(private mobileService:MobileService,private cartService:CartService) { }

  ngOnInit(): void {
    this.mobileService.viewAllMobile().subscribe((data) => {
      this.mobiles = data;
    });
  }
  searchMobiles(){

    if(this.searchTerm===""){

      this.mobileService.viewAllMobile().subscribe((data) => {
        this.mobiles = data;
      });

    }else{

      this.mobiles=this.mobiles.filter(data=>JSON.stringify(data).toLowerCase().includes(this.searchTerm.toLowerCase()));

    }

  }
 
searchTerm:string
 
  
  addToCart(mobileFromViewPage: Mobile) {
             
                   this.cartService.getCartByCustomerId().subscribe((data)=>{

                    console.log("getting data");
                        localStorage.setItem("cartData",data.cartId);

                        let mobilesFromDataBaseInCart:Mobile[]=data.mobiles;

                     let isPresent = data.mobiles.filter(mobile=> mobile.mobileId==mobileFromViewPage.mobileId).length>0;
                      
                  
                          if(isPresent){
                             Swal.fire({text:"Mobile already in cart",icon:"error"});
                            }
                          else{
                            mobilesFromDataBaseInCart.push(mobileFromViewPage);

                            let newCartObject:Cart=
                            {
                              "cartId":JSON.parse(localStorage.getItem("cartData")),
                              "mobiles":mobilesFromDataBaseInCart,
                              "customer":JSON.parse(localStorage.getItem("customer")),
                              "totalAmount":data.totalAmount+JSON.parse(mobileFromViewPage.price)
                            }
                            this.cartService.addToCart(newCartObject).subscribe((data)=>{

                              localStorage.setItem("cartData",data.cartId);
                              Swal.fire({text:"Mobile added to the cart",icon:"success"});
                       });
                            
                          }
                   },
                   (error)=>{
                   let newCartObject:Cart=
                    {
                      "mobiles":[mobileFromViewPage],
                      "customer":JSON.parse(localStorage.getItem("customer")),
                      "totalAmount":JSON.parse(mobileFromViewPage.price)
                    }
                         this.cartService.addToCart(newCartObject).subscribe((data)=>{

                                localStorage.setItem("cartData",data.cartId);
                                Swal.fire({text:"Mobile added to the cart",icon:"success"});
                              })
                   })
            
  }

}


  

