package ch10_jpql;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ch10Main5bulk {
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
            //벌크업데이트 표시

            //team이 뉴진스인 멤버들의 이름을 전부 뉴진스OO으로 변경
//            List<Member> members= em.createQuery("select m FROM Member m join fetch m.team WHERE m.team.name=:teamname", Member.class)
//                    .setParameter("teamname", "뉴진스")
//                    .getResultList();
//            members.stream().forEach(member -> member.setName("뉴진스" + member.getName()));
            //update문이 5번 실행됨.

            //jpql로 직접 update.
            List<Member> members= em.createQuery("select m FROM Member m join fetch m.team WHERE m.team.name=:teamname", Member.class)
                    .setParameter("teamname", "뉴진스")
                    .getResultList();
            int i = em.createQuery(" UPDATE Member m SET m.name= :newjeans ||m.name WHERE m.team.id=2 ")
                    .setParameter("newjeans", "뉴진스")
                    .executeUpdate();  //결과수 반환.

            System.out.println(members);  //문제는 executeUpadte는 엔티티가 아닌 DB에 직접.=>엔티티랑 DB가 안 맞음.

            //현실적으론 벌크 연산 수행 후에는 엔티티초기화

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
