import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { pathToFileURL } from 'url';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AddMobileComponent } from './add-mobile/add-mobile.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MyCartComponent } from './my-cart/my-cart.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { PlaceOrderComponent } from './place-order/place-order.component';
import { ViewMobileComponent } from './view-mobile/view-mobile.component';
import { ViewOrdersComponent } from './view-orders/view-orders.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { CustomerViewMobileComponent } from './customer-view-mobile/customer-view-mobile.component';
import { EditMobileComponent } from './edit-mobile/edit-mobile.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { ContactusComponent } from './contactus/contactus.component';

const routes: Routes = [

  {path:'addmobile',component:AddMobileComponent,canActivate:[AuthGuard]},
 
  {path:'addreview',component:AddReviewComponent,canActivate:[AuthGuard]},
 
  {path:'customer/viewmobiles',component:CustomerViewMobileComponent,canActivate:[AuthGuard]},
 
  {path:'editmobile',component:EditMobileComponent,canActivate:[AuthGuard]},
 
  {path:'error',component:ErrorComponent},
 
  {path:'home',component:HomeComponent},
 
  {path:'login',component:LoginComponent},
  
  {path:'navbar',component:NavbarComponent},

  {path:'mycart',component:MyCartComponent,canActivate:[AuthGuard]},
 
  {path:'myorders',component:MyOrdersComponent,canActivate:[AuthGuard]},
 
  {path:'placeorder',component:PlaceOrderComponent,canActivate:[AuthGuard]},
 
  {path:'registration',component:RegistrationComponent},
 
  {path:'viewmobiles',component:ViewMobileComponent,canActivate:[AuthGuard]},
 
  {path:'vieworders',component:ViewOrdersComponent,canActivate:[AuthGuard]},
 
  {path:'viewreviews',component:ViewReviewComponent,canActivate:[AuthGuard]},

  {path:'dashboard',component:CustomerdashboardComponent,canActivate:[AuthGuard]},
  
  {path:'contactus',component:ContactusComponent},

 
  {path:'',redirectTo:'home',pathMatch:'full'}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
