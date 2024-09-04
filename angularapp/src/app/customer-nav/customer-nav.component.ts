import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-nav',
  templateUrl: './customer-nav.component.html',
  styleUrls: ['./customer-nav.component.css']
})
export class CustomerNavComponent implements OnInit {


  logoutModal : boolean = false;
  constructor(private authService:AuthService,private route:Router) { }

  ngOnInit(): void {
  }

  public logout(){
    //this.authService.isAuthenticated=false;
    localStorage.clear();
     this.route.navigate(['/home']);
  }

  logoutModalCancel(){
    this.logoutModal = false;
  }
  logoutModalPopup(){
    this.logoutModal = true;
  }


}
