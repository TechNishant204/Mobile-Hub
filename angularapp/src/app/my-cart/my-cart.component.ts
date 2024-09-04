import { Component, OnInit } from '@angular/core';
import { Mobile } from '../models/mobile.model';
import { CartService } from '../services/cart.service';
import { Router } from '@angular/router';
import { Cartmobile } from '../models/cartmobile';
import { OrderService } from '../services/order.service';
import { MobileService } from '../services/mobile.service';
import { Cart } from '../models/cart.model';


@Component({
  selector: 'app-my-cart',
  templateUrl: './my-cart.component.html',

  styleUrls: ['./my-cart.component.css']
})
export class MyCartComponent implements OnInit {

  cartMobiles:Cartmobile[]=[];

  mobiles:Mobile[]=[];

  orderPrice:number=0;

   quantity:number=0;
   
   isMobilePresent=true;




  constructor(private cartService:CartService,private route:Router,private orderService:OrderService,private mobileService:MobileService) { }

  ngOnInit(): void {

   this.cartService.getAllMobileFromCart().subscribe(data=>{

       if(data.length==0){

           this.isMobilePresent=false;
       }
    else{
    this.orderPrice=0;
      this.mobiles=data;
      this.cartMobiles=[];
      this.quantity=this.mobiles.length;
      this.updatingCartMobiles();
    }
   }
    );
}
     
      updatingCartMobiles(){

              for(let mobile of this.mobiles){

                this.orderPrice+=parseInt(mobile.price);

                let isChecked:any=this.findMobileById(this.cartMobiles,mobile.mobileId);

                if(isChecked!==undefined){

                        isChecked.quantityWantByUser+=1;
                }

                else{
                  let mobileToAdd = new Cartmobile(
                  mobile.mobileId,
                  mobile.model,
                  mobile.brand,
                  mobile.imageUrl,
                  mobile.description,
                  mobile.price,
                  1
                 );
                 this.cartMobiles.push(mobileToAdd)
              }
          }
      }

      
     findMobileById(mobileArray: Cartmobile[], mobileId: number):Cartmobile | undefined {
        return mobileArray.find(mobile => mobile.mobileId === mobileId);
      }
      
    

  plusQuantity(c:Cartmobile){
  
    this.mobileService.getMobileById(c.mobileId).subscribe((data)=>{
      this.mobiles.push(data);
      let newCartObject:Cart=
      {
        "cartId":JSON.parse(localStorage.getItem("cartData")),
        "mobiles":this.mobiles,
        "customer":JSON.parse(localStorage.getItem("customer")),
        "totalAmount":this.orderPrice
      }
      this.cartService.addToCart(newCartObject).subscribe((data)=>{

          this.ngOnInit();
 });
    })

  }

  minusQuantity(c:Cartmobile){
              
              this.cartService.removeMobileFromCart(c.mobileId).subscribe(data=>{
                this.ngOnInit();
            })

  }
  
  removeMobileFromCart(c:Cartmobile){

    this.orderPrice=this.orderPrice-(parseInt(c.price)*c.quantityWantByUser);

        this.cartService.removeAllMobilesFromCartUsingMobileId(c.mobileId).subscribe((data)=>{

            this.ngOnInit();
        })
  }

        placeOrder(){
         this.orderService.setTotalPrice(this.orderPrice);
         this.orderService.setQuantity(this.quantity);
         this.orderService.setmobiles(this.mobiles);
           this.route.navigate(['/placeorder']);
        }

}
