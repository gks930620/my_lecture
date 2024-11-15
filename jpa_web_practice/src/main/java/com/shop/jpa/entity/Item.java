package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Item {
    public Item(String name, int regularPrice, int stockQuanity) {
        this.name = name;
        this.regularPrice = regularPrice;
        this.stockQuanity = stockQuanity;
    }

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private  Long id;
    private String name;
    private int  regularPrice;
    private int stockQuanity;


    public  void addStock(int count){
        this.stockQuanity+=count;
    }

    public void removeStock(int count){
        int restCount= this.stockQuanity - count;
        if(restCount<0){
            throw new RuntimeException("주문수량이 부족합니다");
        }
        this.stockQuanity=restCount;
    }


}
