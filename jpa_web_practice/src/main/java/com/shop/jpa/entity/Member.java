package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "orders")
@NoArgsConstructor
public class Member {

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Id
    @Column(name = "member_id")
    private String id;  //직접주입
    private String password;
    private  String name;

    @OneToMany(mappedBy = "member" , fetch = FetchType.LAZY)
    private List<Order> orders=new ArrayList<>();



}
