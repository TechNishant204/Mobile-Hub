package com.examly.springapp.model;

import java.util.Date;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long orderId;
     private double orderPrice;
     private int quantity;
     private Date dateOrdered;

     @ManyToMany
     @JoinTable(name="order_mobiles",
     joinColumns = @JoinColumn(name="order_id"),
     inverseJoinColumns = @JoinColumn(name="mobile_id"))
     private List<Mobile> mobiles;

     @ManyToOne
     @JoinColumn(name="customer_id")
     private Customer customer;

}
