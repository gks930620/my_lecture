package ch3_영속성.crud;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.*;

public class Ch3Main1등록 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member1= new Member("m1","창희");
            Member member2= new Member("m2","창희");
            em.persist(member1);
            em.persist(member2);    //1차캐시에만 저장

            tx.commit();   //이때 지금까지의 쿼리들 DB에서 한번에 실행
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
