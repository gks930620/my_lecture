package com.shop.jpa.repositry;

import com.shop.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public List<Member> findAll(){
        List<Member> members = em.createQuery("SELECT m From Member m  ", Member.class).getResultList();
        return members;
    }

    public Member findById(String id){
        Member member = em.find(Member.class, id);
        return member;
    }

    public Member findByIdWithOrders(String id){
        String query=" SELECT m FROM Member m  LEFT  JOIN FETCH m.orders  WHERE m.id=:id";
        Member member = em.createQuery(query ,Member.class).setParameter("id",id).getSingleResult();
        return member;
    }

    public  void save(Member member){
        if( member==null) throw new RuntimeException("null Entity는 등록할 수 없습니다.");
        Member findMember = em.find(Member.class, member.getId());
        if(findMember ==null){
            em.persist(member);
        }else{
            em.merge(member);
        }

    }

    public void delete(Member member){
        Member findMember = em.find(Member.class, member.getId());
        if(findMember!=null ){
            em.remove(member);
        }else{
            throw new RuntimeException("없는 회원입니다.");
        }
    }



}
