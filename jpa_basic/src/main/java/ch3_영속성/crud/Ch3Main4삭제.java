package ch3_영속성.crud;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch3Main4삭제 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //Member 잠깐 grade 필드를 추가해보자..
            Member member = em.find(Member.class, "mem1");
            em.remove(member);
            System.out.println("아직 삭제 쿼리 날리지 않음");
            tx.commit();   //삭제쿼리날라감. DB도 삭제됨
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
