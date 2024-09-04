import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Review } from '../models/review.model';
import { ReviewService } from '../services/review.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  userEmail=localStorage.getItem('email');
 
  reviewForm:FormGroup;
  constructor(private reviewService:ReviewService, private builder:FormBuilder,private router:Router) {

    this.reviewForm = this.builder.group({
      mobileModel:this.builder.control(this.reviewService.reviewMobileModel,Validators.required),
      body:this.builder.control("",Validators.required),
      rating:this.builder.control("",Validators.required)
     
    })
  }
  get mobileModel()
  {
    return this.reviewForm.get('mobileModel');
  }
 
 
  ngOnInit(): void {
    console.log(this.userEmail);
  }
 
 
  public addNewReview(){

    let reviewObject:Review={

            "customer":JSON.parse(localStorage.getItem("customer")),
            "subject":this.reviewForm.get("mobileModel").value,
            "body":this.reviewForm.get("body").value,
            "rating":this.reviewForm.get("rating").value,
            "dateCreated":new Date()
    }

    this.reviewService.addReview(reviewObject).subscribe((data)=>{

             this.router.navigate(['/viewreviews']);
    })


   

}
}


