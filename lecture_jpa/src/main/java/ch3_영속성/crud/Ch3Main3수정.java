package ch3_영속성.crud;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch3Main3수정 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //Member 잠깐 grade 필드를 추가해보자..
            Member member = em.find(Member.class, "mem1");
            member.setGrade("user");   // 영속상태인상태에서 데이터가 변경됨

            tx.commit();   //commit순간에 데이터변경을 감지(스냅샷과 비교)해서 알아서 update 쿼리 실행
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
