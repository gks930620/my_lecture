package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private  Long id;
    private String name;
    private int  regularPrice;
    private int stockQuanity;





}
