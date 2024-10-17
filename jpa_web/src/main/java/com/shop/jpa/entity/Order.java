package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        if(this.member!=null){
            this.member.getOrders().remove(this);  //기존 연관관계 제거
            member.getOrders().add(this);
            this.member = member;
        }
    }

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>();

    private int totalPrice;
    private LocalDate orderDate;

    @Embedded
    private Address address;  //멤버의 address 그냥쓸건지,  아니면 배달주소는 따로할건지..



}
