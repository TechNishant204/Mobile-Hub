import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent implements OnInit {

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
