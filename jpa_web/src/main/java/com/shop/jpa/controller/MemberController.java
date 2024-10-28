package com.shop.jpa.controller;

import com.shop.jpa.entity.Address;
import com.shop.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @PersistenceUnit
    private EntityManagerFactory emf;


    //일단 단순한 CRUD는 했는데 강의 뒤져서 영한이 형님이 어떻게 해쓴ㄴ지 살펴보자.

    @GetMapping("/members")
    public String members(Model model){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Member> members = em.createQuery(" SELECT m FROM Member m ", Member.class).getResultList();
        tx.commit();
        model.addAttribute("members",members);
        return "member/member-list";
    }

    @GetMapping("/member/view/{id}")
    public String member(@PathVariable("id") String id,Model model){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member = em.find(Member.class, id);
        model.addAttribute("member",member);
        tx.commit();
        return "member/member-view";
    }

    @GetMapping("/member/form")
    public String memberForm(Model model){

        return "member/member-form";
    }


    //address는  무시하자.
    @PostMapping("/member/form")
    public String memberInsert(Model model, Member member ){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(member);
        tx.commit();
        return "redirect:/members";
    }

    @GetMapping("/member/modify/{id}")
    public String memberModifyGet(@PathVariable("id") String id,Model model){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member = em.find(Member.class, id);
        model.addAttribute("member",member);
        tx.commit();
        return "member/member-modify";
    }


    @PostMapping("/member/modify")
    public String memberMdofiyPost(Member member, Model model){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member findMember = em.find(Member.class, member.getId());
        findMember.setName(member.getName());
        findMember.setPassword(member.getPassword());
        tx.commit();
        return "redirect:/members";
    }


    @PostMapping("/member/delete")
    public String memberDelete(Member member, Model model){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member findMember = em.find(Member.class, member.getId());
        em.remove(findMember);
        tx.commit();
        return "redirect:/members";
    }
}




