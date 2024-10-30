package com.shop.jpa.entity.item;

import com.shop.jpa.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private  Long id;
    private String name;
    private int  regularPrice;
    private int stockQuanity;
    //상품에서 OrderItem을 참조할 일은 없다.


    //그냥 null이 될 예정.. ㅠㅠ
    @ManyToOne
    @JoinColumn(name = "category_id" )
    private Category category;

    public void setCategory(Category category) {
        if(this.category!=null){
            this.category.getItems().remove(this);
        }
        this.category = category;
        this.category.getItems().add(this);
    }


    //web 만들 때 본격적으로..
    public void modifyInfo(Item item) {
        this.name=item.getName();
        this.regularPrice=item.getRegularPrice();
        this.category=item.getCategory();
    }

    public void addStock(int amount){
        this.stockQuanity+=amount;
    }

    public void removeStock(int amount) {
        int restStock= this.stockQuanity-=amount;
        if (this.stockQuanity < 0) {
            throw new RuntimeException("판매수량이 더 많다. ");
        }
        this.stockQuanity=restStock;
    }


}
