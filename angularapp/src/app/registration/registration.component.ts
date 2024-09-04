import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm:FormGroup;
  newUser:User;
  selectedRole: string = '';

  
  confirmPassword:string="";
//just ignoring for fixing
  constructor(private builder:FormBuilder,private service:AuthService,private route:Router) {
      this.registrationForm = builder.group({
        username:builder.control("",Validators.required),
        email:builder.control("",[Validators.required,Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$')]),
        password:builder.control("",[Validators.required,Validators.minLength(7), 
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$')]),
        confirmPassword:builder.control("",Validators.required),
        mobileNumber:builder.control("",[Validators.required,Validators.pattern('[0-9]{10}')]),
        role:builder.control("",Validators.required)
      },
      {validators:this.passwordMatchValidtor});
  }
  passwordMatchValidtor(form:FormGroup){
    return form.get('password').value===form.get('confirmPassword').value?null:{mismatch:true};
     }

  ngOnInit(): void {
    
  }
  

  addUser(){
    if(this.registrationForm.valid){
      console.log(this.registrationForm.value);
      this.newUser = this.registrationForm.value;
      this.service.register(this.newUser).subscribe(data=>{
        this.registrationForm.reset();
        Swal.fire({text:"Registration Successful",icon:"success"});
        this.route.navigate(['login']);
      },
      (error)=>{

              if(error.error.statusCode ==409){

                //  alert(error.error.errorMessage);
                 Swal.fire({text:error.error.errorMessage,icon:"error"});
              }
             else{
                    this.route.navigate(["/error"]);
             }
      });
      this.newUser = null;
    }
  }

}
