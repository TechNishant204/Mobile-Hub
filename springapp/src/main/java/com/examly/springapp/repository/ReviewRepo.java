package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Review;
@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer>{

   @Query(value = "from Review where customer.user.userId=?1")
   public List<Review> getReviewByUserId(Long userId);

}
