package ch10_jpql;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.*;

import java.util.List;

public class Ch10Main1 {
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
            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.name=:name", Member.class);//엔티티대상쿼리, 대소문자
            Member 민지 = query.setParameter("name", "민지").getSingleResult();   //파라미터는 쿼리 직접X. 파라미터 바인딩 방식으로.
            System.out.println(민지);


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
