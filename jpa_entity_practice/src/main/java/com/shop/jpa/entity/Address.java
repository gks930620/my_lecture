package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String city;
    private String street;
    private String zipcode;
    private String detail;

}
