package ch5_연관관계매핑;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch5Main2조회 {  //등록
    public static void main(String[] args) {
        //이때 initilizer에 team도 같이 데이터 넣자.

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();

            Member findMember = em.find(Member.class, "mem1"); // Team도 한번에 조회.. 상황에 따라 장단점
            System.out.println(findMember);

            Team findTeam=em.find(Team.class,1);
            Team team=findMember.getTeam();
            System.out.println("findTeam===team : " + (findTeam==team));

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
