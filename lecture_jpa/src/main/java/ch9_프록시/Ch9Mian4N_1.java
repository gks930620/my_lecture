package ch9_프록시;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.*;

import java.util.List;

public class Ch9Mian4N_1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            String[] newjeansNames= {"민지","하니","다니엘","해린","혜인"};
            String[] iveNames={"가을","유진","원영","레이","리즈","이서"};
            String[] aespa={"카리나","윈터","닝닝","지젤"};
            persistTeamAndMembers(newjeansNames,"뉴진스",em);
            persistTeamAndMembers(iveNames,"아이브",em);
            persistTeamAndMembers(aespa,"에스파",em);
            em.flush();
            em.clear();  // 엔티티매니저 초기화
            System.out.println("----------------------------------------------");
            // team에 member가 여러명 있는 상황. member는 팀 1개
            // member에서 team조회하는건 문제없음
            // team 조회시 연관된 member들 전부 조회하는 경우

            //즉시로딩 . Eager
            //Team findTeam = em.find(Team.class, 2L);  //사실 JPA가 조인함.

            List<Team> findTeams = em.createQuery("select t From Team t", Team.class).getResultList();
            //jpql로 team을 전부찾음. '어? eager잖아?'  각각의 team에 맞는 member들도 전부 찾음
            // select team쿼리한번,  team개수만큼  select member ..where team.id=member.team_id 쿼리 N 번

            //지연로딩을 하면 바로 N+1문제가 발생하지는 않지만, list를 반복하면 역시 N번 쿼리 실행
            for (Team team : findTeams) {
                System.out.println(team.getMembers());
            }

            //기본적으로 개발할 때는 다 지연로딩 설정 후 나중에 정말 필요한 곳에는 즉시로딩 or  JPQL에 조인쿼리 사용 등

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public  static  void persistTeamAndMembers(String[] names,String group, EntityManager em){
        Team team=new Team();
        team.setName(group);
        em.persist(team);
        for(String name : names){
            Member member= new Member();
            member.setId(name+"id");
            member.setName(name);
            member.setTeam(team);
            em.persist(member);
        }
    }

}
