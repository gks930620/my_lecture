package ch2_basic;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch2Main1수정 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member= new Member("mem1","창희");  //jpa랑 상관없음
            member.setName("민지");
            Member mem1 = em.find(Member.class, "mem1");
            mem1.setName("하니");  //따로 변경메소드 없거 commit 전에 변경감지.

            System.out.println("-----------------");
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
