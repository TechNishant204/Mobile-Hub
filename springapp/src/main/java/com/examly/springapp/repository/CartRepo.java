package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Cart;
import com.examly.springapp.model.Mobile;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

    @Query(value = "from Cart c where customer.customerId=?1")
    //@Query(value = "from Cart")
    public Cart findByCustomerId(Long customerId);

    @Query(value = "from Cart c where customer.user.userId=?1")
    public Cart findByUserId(Long userId);

}
