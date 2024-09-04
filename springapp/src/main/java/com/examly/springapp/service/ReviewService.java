package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Review;

public interface ReviewService {

    Review addReview(Review review);

    Review getReviewById(int reviewId);

    List<Review> getAllReviews();

    List<Review> getReviewByUserId(Long userId);

    Review  deleteReviewById(int reviewId);

    
}
