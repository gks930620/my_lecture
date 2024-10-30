package ch10_jpql;

import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Ch10Main4etchPaging {
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
            //일대다 페이징 중요하지
            // team에 대해서 페이징하는데 데이터가 뻥튀기 되면 안되잖아?
            String jpql= "SELECT m FROM Member m  "; 
            List<Member> members = em.createQuery(jpql, Member.class).setFirstResult(4).setMaxResults(6)
                    .getResultList();
            System.out.println(members);   //다대일은 문제없다. 페치조인을 하던 안하던

            System.out.println("--------------------------------------------------------------------------------------");
            em.flush();
            em.clear();  // 엔티티매니저 초기화
            //경고 로그를 남기고 메모리에서 페이징.... 매우 위험.
            //why? DB데이터가 천만건.  전부 java  메모리에 올리고  그 다음에 membory에서 페이징
            // 실제로는  일대다 join Fetch는 그냥 페이징하면 절대안됨.
            //String jpql2= "SELECT t FROM Team t JOIN FETCH t.members  ";
            String jpql2= "SELECT t FROM Team t";
            List<Team> teams = em.createQuery(jpql2, Team.class).setFirstResult(1).setMaxResults(2)
                    .getResultList();

            for(Team team : teams){
                System.out.println(team.getMembers());
            }
            //이런문제를 해결하는  방법.  BatchSize 사용
            //1 .설정파일에 batch size 설정
            //<property name="hibernate.default_batch_fetch_size" value="20" />
            // 2. LAZY 설정  (어차피 다 LAZY로 설정해야됨. 예상치 못한 쿼리 나가는거방지) , 참고 : join fetch는 LAZY보다 우선한다.
            //3  Team만 조회하기 + team의 members 조회 시점에 where in 쿼리가 나간다.




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
