package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Orders;

public interface OrderService {

    Orders addOrder(Orders order);

    List<Orders> getAllOrders();

    Orders getOrderById(Long orderId);

    List<Orders> getOrdersByCustomerId(Long customerId);

    List<Orders> getOrdersByUserId(Long userId);
}
