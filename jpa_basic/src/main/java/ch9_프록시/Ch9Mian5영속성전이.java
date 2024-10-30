package ch9_프록시;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.*;

public class Ch9Mian5영속성전이 {
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
            //영속성 전이는 연관관계매핑아님. 단순히 영속할 때 같이하냐 안하냐
            // 로직에 맞춰서 설정 해야되지만....개발도중에는 일단 설정안하고 이것도 나중에 한번에? or 그냥 직접 삭제하는게
            // user 삭제=> 유저가 쓴 글 모두 삭제  cascade로 자동으로 해도 좋지만 수동으로 해도 좋고, 로직상 유저만 삭제할수도있고




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
