package com.shop.jpa.entity;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
    //id,item,order,onePrice,  count, delivery

    @Id
    @GeneratedValue
    private Long id;

    private Item item;

    private Order order;

    private int onePrice;

    private int count;

    @Embedded
    private Delivery delivery;


    public int getAllPrice(){
        return onePrice*count;
    }

}
