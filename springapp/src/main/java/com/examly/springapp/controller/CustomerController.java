package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.Customer;
import com.examly.springapp.service.CustomerService;

@RestController
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
@RequestMapping("/api/customer")
public class CustomerController {

   
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){

      this.customerService=customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer){

              Customer   customerObjectFromService= this.customerService.registerCustomer(customer);

              return new ResponseEntity<>(customerObjectFromService,HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){

                List<Customer>  listOfCustomersFromService    = this.customerService.getAllCustomers();

                return new ResponseEntity<>(listOfCustomersFromService,HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable Long userId){

                  Customer      customerObjectByUserId    = this.customerService.getCustomerByUserId(userId);

                return new ResponseEntity<>(customerObjectByUserId,HttpStatus.OK);
    }

}
