package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.CustomerNotFoundException;
import com.examly.springapp.customExceptions.NoCustomersFound;
import com.examly.springapp.customExceptions.RegisterCustomerException;
import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService  {


      @Autowired
      private CustomerRepo customerRepo;

    @Override
    public Customer registerCustomer(Customer customer) {

              Customer   customerObjectAfterSaving =this.customerRepo.save(customer);

               if(customerObjectAfterSaving==null){

                    throw new  RegisterCustomerException("customer registration failed");
               }

               return customerObjectAfterSaving;
     }

    @Override
    public Customer getCustomerById(Long customerId) {


        Customer customerObjectById=  this.customerRepo.findById(customerId).orElse(null);

            if(customerObjectById==null){

                throw new CustomerNotFoundException("customer not found with the given Id "+customerId ); 

            }

            return customerObjectById;
   }

    @Override
    public Customer getCustomerByUserId(Long userId) {

            Optional<Customer>  customerObjectByUserId=this.customerRepo.getCustomerByUserId(userId);
              
                  if(customerObjectByUserId.isPresent()){

                       return customerObjectByUserId.get();
                  }

                  throw new CustomerNotFoundException("Customer not foud with given userID"+userId);
           }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> listOfCustomersFromDataBase=this.customerRepo.findAll();

              if(listOfCustomersFromDataBase.isEmpty()){

                 throw new NoCustomersFound("List is Empty no customers there in data base");
              }

              return listOfCustomersFromDataBase;
     }


    
}
