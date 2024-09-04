package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {


     @Query(value="from Customer where user.userId=?1")
     public Optional<Customer> getCustomerByUserId(Long userId);

}