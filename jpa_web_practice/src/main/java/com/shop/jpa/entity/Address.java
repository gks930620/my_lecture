package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
//@Setter 값타입은 수정말고 늘 새로운값으로 생성하도록.
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String city;
    private String street;
    private String zipcode;
    private String detail;

}
