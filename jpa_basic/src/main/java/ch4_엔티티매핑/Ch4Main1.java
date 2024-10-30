package ch4_엔티티매핑;

import entity.Authority;
import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch4Main1 {

    public static void main(String[] args) {
        //ch4내용은 대부분 entity 부분 살펴보자.
        // 실습은 연관관계 매핑이후부터...  5장 빠르게 해야됨



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member=new Member("m1", "민지");
            em.persist(member);
            member.setAuthority(Authority.USER);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

}
