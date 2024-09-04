package com.examly.springapp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    

    @ManyToMany
    @JoinTable(name="cart_mobiles",
     joinColumns = @JoinColumn(name="cart_id"),
     inverseJoinColumns= @JoinColumn(name="mobile_id"))
    private List<Mobile> mobiles;

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    private double totalAmount;

}
