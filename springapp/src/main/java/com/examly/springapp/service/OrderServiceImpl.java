package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.AddOrderException;
import com.examly.springapp.customExceptions.CommonException;
import com.examly.springapp.customExceptions.GetOrdersException;
import com.examly.springapp.customExceptions.OrderNotFoundException;
import com.examly.springapp.model.Orders;
import com.examly.springapp.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepo orderRepo;

    @Override
    public Orders addOrder(Orders order) {

        try{

        Orders orderObjectAfterSaving=this.orderRepo.save(order); 
           
        if(orderObjectAfterSaving!=null){

            return orderObjectAfterSaving;
        }
        else{
            throw new AddOrderException("Cannot place an order");
            //throw exception that order is not saved
        }
        }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }


    }

    @Override
    public List<Orders> getAllOrders() {

        try{

             List<Orders>   listOfOrdersFromDataBase =  orderRepo.findAll();

             if(listOfOrdersFromDataBase.isEmpty()){
                throw new GetOrdersException("Order list is Empty");
                 //throw exception stating that database has no values
             }
           
             return listOfOrdersFromDataBase;
           }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }

    }

    @Override
    public Orders getOrderById(Long orderId) {

        try
        {
            Orders  orderObjectFromDataBase= this.orderRepo.findById(orderId).orElse(null);

         if(orderObjectFromDataBase!=null){

             return orderObjectFromDataBase;
         }
         else{
            throw new OrderNotFoundException("Order not found with the given ID");
             //throw new exception stating order not found with order id
         }
        }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }
    }
        
    @Override
    public List<Orders> getOrdersByCustomerId(Long customerId) {

        try{

           List<Orders>      listOfOrdersByCustomerId = this.orderRepo.getOrdersByCustomerId(customerId);
           
           if(listOfOrdersByCustomerId.isEmpty()){
               
              throw new OrderNotFoundException("Order not found with the given ID");
              //throw error stating that no orders found with the given customerID
            }

            return listOfOrdersByCustomerId;
           }
        catch(Exception e){
            throw new CommonException("something error occured",e);
        }

    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {

        try{

        List<Orders>   listOfOrdersByUserId=this.orderRepo.getOrdersByUserId(userId);
        
        if(listOfOrdersByUserId.isEmpty()){
            
                    throw new OrderNotFoundException("Order not found with the given ID");
                     //throw error stating that no orders found with the given userID
                 }

        return listOfOrdersByUserId;
        }
        catch(Exception e){

               throw new CommonException("something error occured",e);
        }

    }

}
