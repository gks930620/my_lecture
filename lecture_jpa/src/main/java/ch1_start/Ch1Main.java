package ch1_start;

import entity.Member;
import jakarta.persistence.*;

public class Ch1Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member=new Member();
            em.persist(member);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }



        //main이 끝나면 어차피 다 메모리에서 사라지지만
        //실제 서비스에서는 서버는 켜진채로 하나의 트랜잭션을 처리한다. 그러면 반드시 em이 close되야 메모리 누수가 엇다.
    }
}
