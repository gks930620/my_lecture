package ch8_값타입;

import entity.Address;
import entity.Authority;
import entity.Member;
import etc.JPAInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ch8Main1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        JPAInitializer.firstInsertSetting(emf);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();//트랜잭션 획득!
        try{
            tx.begin();
          
            Member member1=new Member();
            member1.setId("m1");
            member1.setName("민지");
            member1.setAuthority(Authority.USER);

            Member member2=new Member();
            member2.setId("m2");
            member2.setName("하니");
            member2.setAuthority(Authority.USER);
            Address address=new Address("대전","전민1로","33333");
            member1.setAddress(address);
            member2.setAddress(address);
            em.persist(member1);
            em.persist(member2);

            //member1.getAddress().setCity("서울");  //서울로 변경하면?  member2의 주소는?

            //값타입은 불변객체로 만들기, entity에  set할 때는 객체를 새로만들어서.
            //주소 변경을 할 때는 변경되는 주소에 맞는 새로운 Address 객체 생성
            member1.setAddress(new Address("서울","전민1로","33333"));
            

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
