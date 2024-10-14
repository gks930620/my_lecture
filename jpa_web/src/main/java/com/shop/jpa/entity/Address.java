package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Address {
    private  String zipcde;
    private String city;
    private  String street;
    private String detail;

}
