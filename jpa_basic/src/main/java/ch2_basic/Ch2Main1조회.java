package ch2_basic;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch2Main1조회 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member mem1 = em.find(Member.class, "mem1");
            System.out.println(mem1);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
