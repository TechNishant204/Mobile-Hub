import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MobileService } from '../services/mobile.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-mobile',
  templateUrl: './add-mobile.component.html',
  styleUrls: ['./add-mobile.component.css']
})


// export class AddMobileComponent {
//   mobile = {
//     model: '',
//     brand: '',
//     description: '',
//     imageUrl: '',
//     quantity: '',
//     price: ''
//   };

//   constructor(private mobileService: MobileService) {}

//   AddNewMobile() {
//     this.mobileService.addMobile(this.mobile).subscribe(response => {
//       console.log('Mobile added successfully', response);
//     });
//   }
// }

export class AddMobileComponent implements OnInit {
  
  mobileForm:FormGroup;
  constructor(private service:MobileService,private builder:FormBuilder,private router:Router) { 
    this.mobileForm=builder.group({
      model:builder.control("",Validators.required),
      brand:builder.control("",Validators.required),
      imageUrl:builder.control("",Validators.required),
      description:builder.control("",Validators.required),
      price:builder.control("",[Validators.required,Validators.min(1000),Validators.max(150000)]),
      quantity:builder.control("",[Validators.required,Validators.min(1)]),
    })
  }

  public get model(){
    return this.mobileForm.get('model');
  }

  public get brand(){
    return this.mobileForm.get('brand');
  }

  public get imageUrl(){
    return this.mobileForm.get('imageUrl');
  }

  public get description(){
    return this.mobileForm.get('description');
  }

  public get price(){
    return this.mobileForm.get('price');
  }

  public get quantity(){
    return this.mobileForm.get('quantity');
  }

  addNewMobile(){
    if(this.mobileForm.valid){
    return this.service.addMobile(this.mobileForm.value).subscribe(response=>{
      
      Swal.fire({text:"Mobile created successfully",icon:"success"});

           this.router.navigate(['/viewmobiles']);

    },
    (error)=>{

      if(error.error.statusCode ==409){
        Swal.fire({text:error.error.errorMessage,icon:"error"});
     }
     else{

        this.router.navigate(['/error']);
     }
    });
  }
  }
  ngOnInit(): void {
  }

}
