package com.shop.jpa.entity;

import com.shop.jpa.entity.item.Item;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int onePrice;
    private int count;

    @Embedded
    private Delivery delivery;


    public void setOrder(Order order){
        if(this.order!=null ){
            this.order.getOrderItems().remove(this);
            this.order=order;
            order.getOrderItems().add(this);
        }
    }


    public void setItem(Item item){
        this.item=item;    //Item은 참조가 없기때문에 연관관계 제거 안해도 됨
    }


    public  int getAllPrice(){
        return onePrice*count;
    }



}
