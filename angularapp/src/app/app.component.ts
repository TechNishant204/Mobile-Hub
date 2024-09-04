import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { getInterpolationArgsLength } from '@angular/compiler/src/render3/view/util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private authService:AuthService){}
  title = 'angularapp';

  role:string="";

      getRole(){

         if(this.authService.isAuthenticated){

              this.role=JSON.parse(localStorage.getItem("userData")).role;
         }
        return this.authService.isAuthenticated;

      }
       
     
}
