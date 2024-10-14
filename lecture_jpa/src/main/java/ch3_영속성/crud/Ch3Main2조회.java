package ch3_영속성.crud;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch3Main2조회 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member findMember1= em.find(Member.class, "mem1");  //DB에만 있고 1차캐시에는 없던 상황.. select 쿼리 실행
            System.out.println("findMember는 DB전에 실행됩니다.");
            Member findMember2= em.find(Member.class, "mem1");  //1차캐시에 있는 경우 쿼리 실행X ..약간의 성능상 이점.

            System.out.println("같은 아이디로 찾은 member가 같은가? : " + (findMember1==findMember2));


            tx.commit();   //이때 지금까지의 쿼리들 DB에서 한번에 실행
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
