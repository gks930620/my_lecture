package ch3_영속성;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Ch3Main5Flush {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member=new Member("m1","민지");
            Member member2=new Member("m2","하니");
            em.persist(member);
            em.persist(member2);
            em.flush();  //이 때 쿼리가 날라감.   쿼리가 실행됐을 뿐 commit은 아니니까 DB에는 아직 반영안됨
            new Scanner(System.in).next();
            Member member3=new Member("m3","다니");
            Member member4=new Member("m4","해린");
            em.persist(member3);
            em.persist(member4);

            tx.commit();   //tx.commit()은 flush 하고 commit.  flush할 때 모아놨둰 쿼리가 실행되는거임
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
