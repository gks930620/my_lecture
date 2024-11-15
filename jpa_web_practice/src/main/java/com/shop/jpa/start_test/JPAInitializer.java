package com.shop.jpa.start_test;

import com.shop.jpa.entity.Item;
import com.shop.jpa.entity.Member;
import com.shop.jpa.entity.Order;
import com.shop.jpa.entity.OrderItem;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;


/**
 * 여기는 spring을 배웠든 안 배웠든 처음 시작시 더미데이터 넣는 코드
 */
@Component
public class JPAInitializer {
    @PersistenceUnit
    private EntityManagerFactory emf;
    @PostConstruct
    public void init() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TestEntity test = new TestEntity("test");
            em.persist(test);

            Member member=new Member("m1","히니","1004");
            em.persist(member);

            Item item1=new Item("상품1",10000,100);
            Item item2=new Item("상품2",30000,200);
            Item item3=new Item("상품3",20000,300);
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);

            Order order1 = new Order();
            order1.setMember(member);
            OrderItem orderItem11= new OrderItem(item1,1);
            orderItem11.setOrder(order1);
            OrderItem orderItem12= new OrderItem(item2,2);
            orderItem12.setOrder(order1);
            OrderItem orderItem13= new OrderItem(item3,3);
            orderItem13.setOrder(order1);
            order1.goOrder();
            em.persist(order1);
            em.persist(orderItem11);
            em.persist(orderItem12);
            em.persist(orderItem13);

            Order order2 = new Order();
            order2.setMember(member);
            OrderItem orderItem21= new OrderItem(item1,10);
            orderItem21.setOrder(order2);
            OrderItem orderItem22= new OrderItem(item2,20);
            orderItem22.setOrder(order2);
            OrderItem orderItem23= new OrderItem(item3,30);
            orderItem23.setOrder(order2);
            order2.goOrder();
            em.persist(order2);
            em.persist(orderItem21);
            em.persist(orderItem22);
            em.persist(orderItem23);

            //m1멤버가 주문을 2번하고,  각각의 주문에서 상품을 3개씩 주문함.

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
