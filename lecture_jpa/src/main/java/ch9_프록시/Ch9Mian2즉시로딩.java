package ch9_프록시;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch9Mian2즉시로딩 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //fetch가 Eager이면  즉시로딩. 대부분의 JPA구현체는 조인쿼리사용
            Member member=new Member();
            member.setId("m1");
            member.setName("민지");
            Team team=new Team();
            team.setName("뉴진스");
           member.setTeam(team);

            em.persist(team);
            em.persist(member);
            em.flush();
            em.clear();  // 엔티티매니저 초기화
            System.out.println("--------------------------------------------------------");
            Member findMember= em.find(Member.class, "m1");   //즉시일 때는 team과 조인함.
            // member id가 "m1"이고 team이  null경우.
            // id를 "m1"으로 조회해도  내부 join을 하면 팀이 없는 회원은 조회 안되겠지?
            // 그래서 JPA는 기본적으로 외부조인함.
            // 내부조인을 하고싶으면  member.team에 @JoinColumn(nullable=false) 추가.
            


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
