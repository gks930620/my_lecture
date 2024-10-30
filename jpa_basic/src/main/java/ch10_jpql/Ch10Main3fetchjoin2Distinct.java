package ch10_jpql;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ch10Main3fetchjoin2Distinct {
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            String jpql=" SELECT  t FROM Team t  JOIN  FETCH t.members m WHERE t.name=:teamname";
            List<Team> teams = em.createQuery(jpql, Team.class)
                    .setParameter("teamname", "뉴진스")
                    .getResultList();
            for(Team team : teams){
                System.out.println("teamName : " + team.getName());
                for(Member member : team.getMembers()){
                    System.out.println(team.getName() + " : " + member.getName()  );
                }
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            //hibernate6부터는  DISTINCT를 쓰지않아도  적용된것처럼
            // 죽, entity 개수에 맞는 결과값을 자동으로



            em.flush();
            em.clear();  // 엔티티매니저 초기화
            //이번엔 fetch아닌 일반 조인. 일대다든 다대일이든 일반조인은 연관된 엔티티를 함께 조회X. => 이후 Member 쿼리 실행
            String sql=" SELECT t FROM Team t  JOIN  t.members m WHERE t.name=:teamname";
            List<Team> teamsNotFetch = em.createQuery(sql, Team.class)
                    .setParameter("teamname", "뉴진스")
                    .getResultList();
            for(Team team : teamsNotFetch){
                System.out.println("teamNotFetch : " + team.getName());
                for(Member member : team.getMembers()){
                    System.out.println(team.getName() + " : " + member.getName()  );
                }
            }



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
