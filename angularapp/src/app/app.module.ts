import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AddMobileComponent } from './add-mobile/add-mobile.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { AuthguardComponent } from './authguard/authguard.component';
import { CustomerViewMobileComponent } from './customer-view-mobile/customer-view-mobile.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditMobileComponent } from './edit-mobile/edit-mobile.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MyCartComponent } from './my-cart/my-cart.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PlaceOrderComponent } from './place-order/place-order.component';
import { RegistrationComponent } from './registration/registration.component';
import { ViewMobileComponent } from './view-mobile/view-mobile.component';
import { ViewOrdersComponent } from './view-orders/view-orders.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import { InterceptorService } from './services/interceptor.service';
import { AdminNavComponent } from './admin-nav/admin-nav.component';
import { CustomerNavComponent } from './customer-nav/customer-nav.component';
import { ContactusComponent } from './contactus/contactus.component';
import { AboutusComponent } from './aboutus/aboutus.component';

@NgModule({
  declarations: [
    AppComponent,
    AddMobileComponent,
    AddReviewComponent,
    AuthguardComponent,
    CustomerViewMobileComponent,
    CustomerdashboardComponent,
    DashboardComponent,
    EditMobileComponent,
    ErrorComponent,
    HomeComponent,
    LoginComponent,
    MyCartComponent,
    MyOrdersComponent,
    NavbarComponent,
    PlaceOrderComponent,
    RegistrationComponent,
    ViewMobileComponent,
    ViewOrdersComponent,
    ViewReviewComponent,
    AdminNavComponent,
    CustomerNavComponent,
    ContactusComponent,
    AboutusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers: [{provide: HTTP_INTERCEPTORS,useClass:InterceptorService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
