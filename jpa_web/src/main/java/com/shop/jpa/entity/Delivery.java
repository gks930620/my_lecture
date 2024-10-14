package com.shop.jpa.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private OrderItem orderItem;

    private DeliveryStatus deliveryStatus;

    private LocalDate arriveDate;

}
