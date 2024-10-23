package ch10_jpql;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ch10Main3fetchjoin {
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

            String sql=" SELECT m FROM Member m join fetch m.team "; //그냥 join과 차이는 team도 엔티티 등록함. eager,lazy상관없이
            List<Member> memberList = em.createQuery(sql, Member.class).getResultList();
            System.out.println(memberList);

            String sql2="SELECT m FROM Member m join fetch m.team t WHERE t.name = :teamname"; //그냥 join과 차이는 team도 엔티티 등록함. eager,lazy상관없이
            List<Member> memberList2 = em.createQuery(sql2, Member.class)
                            .setParameter("teamname","뉴진스")
                            .getResultList();
            System.out.println(memberList2);


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
