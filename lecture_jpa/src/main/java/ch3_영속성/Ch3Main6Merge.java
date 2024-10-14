package ch3_영속성;

import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch3Main6Merge {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
            //em.detach(entity) 특정 엔티티만 준영속 상태로 전환.
            //em.clear() : 영속성 컨텍스트를 초기화
            //em.close() : 영속성 컨텍스트가 종료됨.
            Member member = em.find(Member.class, "mem1");
            em.detach(member);
            member.setGrade("user");
            Member mergeMember = em.merge(member);   //mergeMember의  ID가 mem1임
            System.out.println(member==mergeMember);
            System.out.println("영속성안에  member : " + em.contains(member));
            System.out.println("영속성안에  mergeMember : " + em.contains(mergeMember));
            //이 때 mergeMember의 스냅샷은 맨 처음 member의 스냅샷(id가 mem1으로 똑같음)..  1차캐시를 각 줄에서 어떻게 진행되는지 그려보면됨.

            Member newMember=new Member("m1", "민지");
            em.merge(newMember);   //1차캐시에 'member가 없음.' ==> DB조회해봄 ==>여전히 없음. 1차 캐시 등록 및 Insert쿼리 생성

            tx.commit();  //member : update 쿼리 안함,   mergeMember에 대해서 update 한거임.
            //merge는 비영속,준영속 상관안함. 1차캐시의 식별자로 판단. 병합은 save or update

            //detach,clear 안씀. 준영속은 다 close때문에 생김. 개발자가 직접 준영속으로 만들일 없음.

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
