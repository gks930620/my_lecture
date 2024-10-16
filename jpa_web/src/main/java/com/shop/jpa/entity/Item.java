package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private  Long id;
    private String name;
    private int  regularPrice;
    private int stockQuanity;
    //상품에서 OrderItem을 참조할 일은 없다.

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory(Category category) {
        if(this.category!=null){
            this.category.getItems().remove(this);
        }
        this.category = category;
        this.category.getItems().add(this);
    }
}
