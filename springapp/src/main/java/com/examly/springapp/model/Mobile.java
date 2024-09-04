package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mobileId;
    private String model;
    private String brand;
    private String imageUrl;
    private String description;
    private String price;
    private int quantity;

    
}
