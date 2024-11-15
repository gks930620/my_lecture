package com.shop.jpa.service;

import com.shop.jpa.entity.Member;
import com.shop.jpa.repositry.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getMemberList(){
        List<Member> members = memberRepository.findAll();
        return members;
    }


    @Transactional(readOnly = true)
    public  Member getMember(String id){
        Member member = memberRepository.findById(id);
        return member;
    }


    @Transactional(readOnly = true)  // 랜잭션 범위의 영속성 컨텍스트 전략 때문에  조회할 때도 tarsactional 하는게..
    public  Member getMemberWithOrders(String id){
        Member member = memberRepository.findByIdWithOrders(id);
        return member;
    }


    @Transactional(readOnly = false)
    public void updateMember(Member member){
        //memberRepository.save(member);   merge보단 기본적으로 변경감지를 하는게 맞음
        Member findMember = memberRepository.findById(member.getId());
        findMember.setName(member.getName());
        findMember.setPassword(member.getPassword());
    }

    @Transactional(readOnly = false)
    public void deleteMember(Member member){
        memberRepository.delete(member);
    }


    @Transactional(readOnly = false)
    public void  insertMember(Member member){
        memberRepository.save(member);
    }




}
