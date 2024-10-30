package ch9_프록시;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch9Mian1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member mem1 = em.getReference(Member.class, "mem1"); //no select query
            //데이터베이스를 미룸. 실제 mem1을 사용할때 조회됨
            // 엔티티매니저에 엔티티가 있으면(영속) getReference()메소드는 굳이 프록시객체 반환이 아니라 엔티티반환



            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
