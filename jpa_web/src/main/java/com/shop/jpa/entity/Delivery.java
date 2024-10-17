package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
//@Setter   //값 타입은 언제나 new
public class Delivery {
    //private OrderItem   uml에서 이거 지우자.  값 타입으로 써야함

    //UML도  delivery 값 타입으로 화게 OrderItem 삭제하고... 뭐 이것저것 해보자..

    //목표 : JPA 매핑 끝까지
    private  DeliveryStatus deliveryStatus;
    private LocalDate arriveDate;

}



