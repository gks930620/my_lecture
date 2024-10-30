package com.shop.jpa.entity;

import com.shop.jpa.entity.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Setter
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items=new ArrayList<>();

}
