import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { ReviewService } from '../services/review.service';
import { Review } from '../models/review.model';

@Component({
  selector: 'app-view-review',
  templateUrl: './view-review.component.html',
  styleUrls: ['./view-review.component.css']
})
export class ViewReviewComponent implements OnInit {
  reviews:Review[] = [];

  constructor(private reviewService:ReviewService) { }

  ngOnInit(): void {
    
        this.reviewService.viewAllReviews().subscribe((data)=>{
          
              this.reviews=data;
        })
  }
  
  
    
  
}
