package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.CommonException;
import com.examly.springapp.customExceptions.GetReviewsException;
import com.examly.springapp.customExceptions.ReviewNotDeletedException;
import com.examly.springapp.customExceptions.ReviewNotFoundException;
import com.examly.springapp.customExceptions.addReviewException;
import com.examly.springapp.model.Review;
import com.examly.springapp.repository.ReviewRepo;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private  ReviewRepo reviewRepo;

    @Override
    public Review addReview(Review review) {

        try{
        Review reviewObjectAfterSaving=reviewRepo.save(review);

                    if(reviewObjectAfterSaving!=null){
                        return reviewObjectAfterSaving;
                    }
                    else{
                        throw new addReviewException("Review is not added");
                        //throw new Exception 
                    }
                }
        catch(Exception e){

            throw new CommonException("something error occured",e);
        }

        
    }
    @Override
    public Review getReviewById(int reviewId) {

        try{
        Review existingReview=reviewRepo.findById(reviewId).orElse(null);
        if(existingReview!=null){
            return existingReview;
        }
        else{
            throw new ReviewNotFoundException("Review not found with the given ID");
        }
        }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }

    }
    
    @Override
    public List<Review> getAllReviews() {

        try{
        List<Review> listOfReviews=this.reviewRepo.findAll();
          
                  if(listOfReviews.isEmpty()){
                    throw new GetReviewsException("Review list is empty");
                    //throw new Exception
                }
                return listOfReviews;
            }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }
       }



            @Override
            public List<Review> getReviewByUserId(Long userId) {
        // if(reviewByUserId!=null){
            //     return reviewByUserId;
            // }else{
        //     return null;
        //     //throw Exception element is not found using the given ID;
        // }
        return null;
    }
    
    @Override
    public Review deleteReviewById(int reviewId){

        try{

        Review  reviewBeforeDeleting= this.getReviewById(reviewId);
        
        if(reviewBeforeDeleting!=null){
            
            this.reviewRepo.deleteById(reviewId);
            
            if(this.reviewRepo.existsById(reviewId)){

                throw new ReviewNotDeletedException("Review cannot be removed");
                // throw error
            }
            
            return reviewBeforeDeleting;
            
                }
                else{
                    throw new ReviewNotFoundException("Review not found with the given ID");
                    // throw exception that element is not found with the given ID;
                }
            }
       
       catch(Exception e){
        throw new CommonException("something error occured",e);
       }

             
            
    }
}
