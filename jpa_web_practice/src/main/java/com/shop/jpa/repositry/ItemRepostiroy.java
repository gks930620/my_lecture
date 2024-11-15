package com.shop.jpa.repositry;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemRepostiroy {
    private  final EntityManager em;

    //등록, 조회 , 삭제,  (수정X)    ITEM 빠르게 하고,  orderItem 빠르게 하고, Order 신경써서(cancel같은거)




}
