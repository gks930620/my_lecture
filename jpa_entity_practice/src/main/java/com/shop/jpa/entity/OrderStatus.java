package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    READY,PROGRESS,CANCEL, COMPLETE
}
