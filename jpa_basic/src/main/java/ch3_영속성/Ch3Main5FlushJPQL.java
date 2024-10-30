package ch3_영속성;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ch3Main5FlushJPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //jpql을 실행하면 flush됨. why?
            // jpql은 객체 대상으로 쿼리를 쓰는거지만,  개발자 입장
            // 실제로는 SQL을 날려서 데이터를 조회함.
            Member member=new Member("m1","민지");
            Member member2=new Member("m2","하니");
            em.persist(member);
            em.persist(member2);

            List<Member> resultList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
            System.out.println("이 전에 insert 쿼리 날라가고  jpql에 맞는 select 쿼리도 날라감");
            //mem1, m1,m2 모두 나오길 기대했는데 사실 DB에는 mem1만 있는 상황이다??? 논리적으로 안 맞음.
            // 그래서  jpql을 실행하면 자동으로 flush() 해서 그동안의 모아눴던 쿼리를 날려 DB를 최신화 하고 거기에 맞춘 jpql-sql을 실행  (물론 이때도 flush지 commit은 아님)
                        
            member.setGrade("user");

            tx.commit();   //여기서는 m1 update 쿼리만 실행됨.


            //참고로 flush는 쓰기 지연 쿼리를 날린다는의미에서의 flsuh지.   영속상태를 날리는것이 아님.  영속상태는 유지되고 있음
            //find는  DB보다 먼저 1차캐시를 조회하니까..flush 안됨.
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
