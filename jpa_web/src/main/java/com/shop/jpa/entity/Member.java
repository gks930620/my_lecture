package com.shop.jpa.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.List;


//ch4 엔티티매핑부분 실습하고있는거임..  일단 기본 엔티티매핑만 해보자. 연관관계는 ch5하고나서 .
@Entity
@Getter
public class Member {
    //id,password,name, address,orders
    @Id
    private String id;   // 직접주입방식

    private  String password;
    private String name;

    @Embedded
    private Address address;

    private List<Order> orders;


}
