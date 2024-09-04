package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.Orders;
import com.examly.springapp.service.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
public class OrderController {   

    
    private  OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){

         this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order)
    {
        System.out.println("------------------------------------"+order);
        Orders newOrder = orderService.addOrder(order);     
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
      
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders()
    {
        List<Orders> mobileList = orderService.getAllOrders();
      
    
            return ResponseEntity.status(HttpStatus.OK).body(mobileList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getOrderByUserId(@PathVariable Long userId)
    {

        List<Orders>   listOfOrdersByUserId= this.orderService.getOrdersByUserId(userId);

         return new ResponseEntity<>(listOfOrdersByUserId,HttpStatus.OK);
    }

    // @GetMapping("")
    // public ResponseEntity<?> ViewOrderByOrderId(@PathVariable Long orderId)
    // {

    // }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Orders>> ViewOrderByCustomerId(@PathVariable Long customerId)
    {
          return new ResponseEntity<>(this.orderService.getOrdersByCustomerId(customerId),HttpStatus.OK);
    }
}
