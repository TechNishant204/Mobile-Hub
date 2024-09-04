package com.examly.springapp.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {


      private UserRepo userRepo;

      @Autowired
      public MyUserDetailsService(UserRepo userRepo){

          this.userRepo=userRepo;
      }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      

        Optional<User> userDetailsFromDataBase=this.userRepo.loadUserDetailsByUserName(username);

          if(userDetailsFromDataBase.isPresent()){  
             return org.springframework.security.core.userdetails.User.builder()
                                   .username(userDetailsFromDataBase.get().getUsername())
                                   .password(userDetailsFromDataBase.get().getPassword())
                                   .roles(userDetailsFromDataBase.get().getRole())
                                   .build();
          }

        throw new UsernameNotFoundException("no user found with the give user name");



     }

}
