package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private int totalPrice;   //넣을 때 잘 넣으면 됨.
    private LocalDate orderDate;

    @Embedded
    @Setter
    private Address address;  //멤버의 address 그냥쓸건지,  아니면 배달주소는 따로할건지..


    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus orderStatus; //orderItem-delivery가 하나라도 complete라면 취소 못함
    //배달료나 구매금액때문에 보통 order에서 취소 관리.
    // 편의점에서 한번에 구매한 품목들도 개별물품에 대해 환불이 안되는것처럼.




}
