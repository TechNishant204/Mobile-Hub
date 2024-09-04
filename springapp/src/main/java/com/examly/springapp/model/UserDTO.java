package com.examly.springapp.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

     private Long userId;
     private String username;
     private String email;
     private String role;
     private String jsonToken;

}
