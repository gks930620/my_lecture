package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "orders")
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;  //직접주입
    private String password;
    private  String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

    @Embedded
    private Address address;

}
