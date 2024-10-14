package ch2_basic;

import entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch2Main1등록 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member=new Member("mem1","창희");
            em.persist(member);   //em에  등록   DB에서 조회가능.




            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
