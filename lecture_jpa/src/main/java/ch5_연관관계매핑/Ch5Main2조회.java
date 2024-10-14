package ch5_연관관계매핑;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch5Main1등록 {  //등록
    public static void main(String[] args) {
        //연관관계 매핑까지 하고 실습예제랑 같이 commit하자.
        //참고로 다양한 연관관계에서 카테고리 item은 다대다가 아니라 다대일로 할거임.

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Team team=new Team();
            team.setName("teamA");
            em.persist(team);
            Member member=new Member("m1" , "창희");
            member.setTeam(team);
            em.persist(member);  //알아서 외래키 세팅해서 등록

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
