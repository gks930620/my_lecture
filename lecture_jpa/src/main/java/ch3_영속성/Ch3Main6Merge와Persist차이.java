package ch3_영속성;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch3Main6Merge와Persist차이 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member = new Member("mem1" ,"창희");  //DB내용이랑 같음
            //Member member = new Member("mem1" ,"민지");  //DB내용이랑 다름
            em.merge(member);   // 1차캐시에 있는지없는지 찾고 select를 함. 없으면 insert.
            //em.persist(member);  //묻따 insert
            tx.commit();

            //헷갈리는건 나중에 정리하시고
            // persist는 insert 무조건 함
            // merge는 1차캐시 상황에 따라 select, insert를 할수도있고 안할수도 있고...

            //update는 merge랑 관련있는게아니라 변경사항이랑 관련있다!!


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
