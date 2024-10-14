package com.shop.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Order {
    //id,member,orderItems, totalPrice,orderDate,Address
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  Member member;
    private List<OrderItem> orderItems;
    private int totalPrice;
    private LocalDate orderDate;
    private Address address;

}
