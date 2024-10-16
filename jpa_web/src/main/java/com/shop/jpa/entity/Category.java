package com.shop.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//사실 category같은 경우 변경(추가,수정), 상위/하위 등이 없다면 ,  Enum으로 관리해도 됨.
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
