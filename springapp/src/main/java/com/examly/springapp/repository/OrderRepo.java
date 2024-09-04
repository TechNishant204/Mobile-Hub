package com.examly.springapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Long>{
    

    @Query(value="from Orders where customer.customerId=?1")
    public List<Orders>  getOrdersByCustomerId(Long customerId);


    @Query(value="from Orders where customer.user.userId=?1")
    public List<Orders>  getOrdersByUserId(Long userId);

}
