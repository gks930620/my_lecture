package ch10_jpql;

import dto.MemberDTO;
import entity.Member;
import entity.Team;
import etc.JPAInitializer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ch10Main2New {
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
            List<Member> members = em.createQuery("SELECT m.id,m.name FROM Member m ", Member.class).getResultList();
            List<MemberDTO> collect = members.stream().map(Member::toMemberDTO).toList();  //17부터
            System.out.println(collect);

            List<MemberDTO> memberDTOs = em.createQuery("SELECT new dto.MemberDTO(m.id,m.name) FROM Member m ", MemberDTO.class).getResultList();
            System.out.println(memberDTOs);
            //풀 패키지명을 다 써야돼서 난 잘 안쓰고 DTO,ENTITY에 from, to of 메소드 등을 정확히 만드는 편
            //그래도 DTO만들필요없고 간단한 경우 new 사용해야할 일이 있음.



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
