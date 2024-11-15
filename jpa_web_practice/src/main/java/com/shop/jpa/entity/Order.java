package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
@NoArgsConstructor
@ToString(exclude = {"member", "orderItems"})
public class Order {
    //상품이 한번에 도착한다고 가정합시다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>();


    public void setMember(Member member){
        if( this.member!=null){
            this.member.getOrders().remove(this);
        }
        this.member=member;
        this.member.getOrders().add(this);
    }


    public void goOrder() {
        this.orderStatus=OrderStatus.READY;
        for(OrderItem orderItem : orderItems){
            Item item = orderItem.getItem();
            item.removeStock(orderItem.getCount());
            this.totalPrice+= orderItem.getCount()*item.getRegularPrice();
        }
    }

    public void cancel(){
        this.orderStatus=OrderStatus.CANCEL;
        for(OrderItem orderItem : orderItems){
            Item item = orderItem.getItem();
            item.addStock(orderItem.getCount());
        }
    }

}
