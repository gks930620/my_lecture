package ch2_basic;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.*;

import java.util.List;

public class Ch2Main2JPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //JPQL : 실제 DB에 대해서 쿼리를 실행하는 것이 아닌 Entity에 대해서 쿼리를 실행함
            //JPA가 나중에 JPQL을 보고 DB에 맞춰서 실제 쿼리 실행
            // 일반적인 쿼리랑 비슷. 등록,수정,삭제는 JPQL 할 게 없는데 조회의 경우 다양한 상황에 맞는 조회를 하기 위해

            TypedQuery<Member> jpql = em.createQuery("SELECT m FROM Member m", Member.class);//테이블이 아닌 entity이기 때문에 'M'ember
            List<Member> memberList = jpql.getResultList();
            System.out.println(memberList);


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
