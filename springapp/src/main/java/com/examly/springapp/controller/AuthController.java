package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.MyUserDetailsService;
import com.examly.springapp.model.ConstantVariableClass;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserDTO;
import com.examly.springapp.service.UserService;

@RestController
@CrossOrigin(origins = ConstantVariableClass.FRONTEND_URL,allowedHeaders="*")
@RequestMapping("/api")
public class AuthController {


    
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private MyUserDetailsService myUserDetailsService;
    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
    JwtUtils jwtUtils,
    MyUserDetailsService myUserDetailsService,
    UserService userService){
      this.authenticationManager=authenticationManager;
      this.jwtUtils=jwtUtils;
      this.myUserDetailsService=myUserDetailsService;
      this.userService=userService;
    }



    @PostMapping("/register")
    public ResponseEntity<User> userRegister(@RequestBody User user){

        User  userObjectFromservice= this.userService.registerUser(user);

       return new ResponseEntity<>(userObjectFromservice,HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO>  login(@RequestBody User user){

            Authentication    authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword()
                  ));
                  if(authentication.isAuthenticated()){
                  UserDTO userDetailsFromService= userService.loginUser(user);

                      userDetailsFromService.setJsonToken(jwtUtils.generateToken(myUserDetailsService.loadUserByUsername(user.getUsername())));
                        
                    return  new ResponseEntity<>(userDetailsFromService,HttpStatus.OK);
                  }
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
