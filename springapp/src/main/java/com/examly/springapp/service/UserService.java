package com.examly.springapp.service;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;

public interface UserService {


     public User registerUser(User user);

     public UserDTO loginUser(User user);

}
