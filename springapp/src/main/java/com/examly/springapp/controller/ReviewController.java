package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.Review;
import com.examly.springapp.service.ReviewService;
import com.examly.springapp.service.ReviewServiceImpl;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        return new ResponseEntity<>(reviewService.addReview(review),HttpStatus.CREATED);
    }
    // @GetMapping("api/{reviewId}")
    // public ResponseEntity<Review> getReviewById(@PathVariable("reviewId") int reviewId){
    //     return new ResponseEntity<>(reviewServiceImpl.getReviewById(reviewId),HttpStatus.OK);
    // }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(),HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Review> deleteReviewById(@PathVariable("reviewId") Integer reviewId){
        return new ResponseEntity<>(reviewService.deleteReviewById(reviewId),HttpStatus.OK);
    }

     
}
