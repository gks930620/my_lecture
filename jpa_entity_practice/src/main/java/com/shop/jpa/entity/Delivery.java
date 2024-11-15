package com.shop.jpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Enumerated(EnumType.STRING)
    private  DeliveryStatus deliveryStatus;
    private LocalDate arriveDate;
}



