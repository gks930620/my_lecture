package ch9_프록시;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch9Mian3지연로딩 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
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
            Member findMember= em.find(Member.class, "m1");   //지연일때는 team은 프록시객체
            Team findTeam=findMember.getTeam();
            System.out.println(findTeam.getClass()); //  proxy 객체가 나옴
            findTeam.getName();  //이 때 실제 select 쿼리 실행.    엔티티매니저에 실제 team 객체 등록됨.
            // 여전히 member의 team은 프록시. 이 프록시 객체는 실제 team객체에 대한 정보가 있음.


            //즉시로딩, 지연로딩은 장 단점이 있음.    상황에 따라 올바른 로딩방식 선택해야함.
            //사용빈도, 연관관계데이터 량,  N+1 등
            //추천 : 개발은 전부 지연로딩,   이후 개발 완료단계즈음에 꼭 필요한 곳에 즉시로딩 설정
            //SQL 직접 개발방식은 이런 유연함이 어렵다..


            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
