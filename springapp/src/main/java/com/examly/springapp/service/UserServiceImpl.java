package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.customExceptions.AddUserException;
import com.examly.springapp.customExceptions.DuplicateEmailException;
import com.examly.springapp.customExceptions.DuplicateMobileNumberException;
import com.examly.springapp.customExceptions.DuplicateUserException;
import com.examly.springapp.customExceptions.UserNotFoundWithNameException;
import com.examly.springapp.model.ErrorDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import com.examly.springapp.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDTO userDTO;

    @Autowired
    private ErrorDTO errorDTO;


            
    @Override
    public User registerUser(User user) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            if(this.userRepo.loadUserDetailsByUserName(user.getUsername()).isPresent()){

                  throw new DuplicateUserException("user already exists with given username use different username");
            }

            if(this.userRepo.existsByEmail(user.getEmail())){

                  throw new DuplicateEmailException("Email id already taken use different email");

            }
            else if(this.userRepo.existsByMobileNumber(user.getMobileNumber())){

                  throw new DuplicateMobileNumberException("Mobile number already taken use different mobile number");
            }

            try{

            User   userObjectAfterSaving = this.userRepo.save(user);

            return userObjectAfterSaving;
            }
            catch(Exception e){

                  throw new AddUserException("User is not added to the database",e);
            }
           
   }

    @Override
    public UserDTO loginUser(User user) {

            User   userDetailsByUserName= userRepo.loadUserDetailsByUserName(user.getUsername()).orElse(null);


                  if(userDetailsByUserName!=null){

                       userDTO.setEmail(userDetailsByUserName.getEmail());
                       userDTO.setUserId(userDetailsByUserName.getUserId());
                       userDTO.setUsername(userDetailsByUserName.getUsername());
                       userDTO.setRole(userDetailsByUserName.getRole());

                    return userDTO;
                  }
                  else{
                        log.error("username or password is incorrect error"+user);
                     throw new UserNotFoundWithNameException("User is not found with the given name");
                  }

       
    }

}
