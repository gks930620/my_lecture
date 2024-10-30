package ch5_연관관계매핑;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch5Main3양방향연관관계 {  //등록
    public static void main(String[] args) {
        //이때 initilizer에 team도 같이 데이터 넣자.

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            Member member=em.find(Member.class, "mem1");
            System.out.println("member의 원래 팀 : " + member.getTeam().getName());
            Team newTeam=new Team();
            newTeam.setName("team2");
            em.persist(newTeam);    //변경감지 일어날려면 newTeam도 영속이어야..

            member.setTeam(newTeam);
            System.out.println("member의 원래 팀 : " + member.getTeam().getName());


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
