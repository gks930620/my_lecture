package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    READY,CANCEL, COMPLETE   //READY일 때 수량감소, cancel하면 수량 원래대로
}
