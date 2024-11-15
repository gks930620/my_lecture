package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"item","order"})
public class OrderItem {

    public OrderItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;

    public  void setOrder(Order order){
        if(this.order!=null){
            this.order.getOrderItems().remove(this);
        }
        this.order=order;
        this.order.getOrderItems().add(this);

    }





}
