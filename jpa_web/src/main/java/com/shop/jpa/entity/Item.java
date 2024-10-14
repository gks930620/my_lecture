package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Item {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private  Long id;
    private String name;
    private int  regularPrice;
    private int stockQuanity;
    //상품에서 OrderItem을 참조할 일은 없다.
}
