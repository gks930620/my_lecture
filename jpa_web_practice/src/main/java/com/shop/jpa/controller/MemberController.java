package com.shop.jpa.controller;

import com.shop.jpa.entity.Member;
import com.shop.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private  final MemberService memberService;

    @RequestMapping("/members")
    public String getMembers(Model model){
        List<Member> members = memberService.getMemberList();
        model.addAttribute("members", members);
        return "member/member-list";
    }

    @RequestMapping("/member/view/{id}")
    public String getMember(Model model, @PathVariable("id") String id){
        Member member = memberService.getMemberWithOrders(id);
        model.addAttribute("member", member);
        model.addAttribute("orders", member.getOrders());
        return "member/member-view";
    }



    @GetMapping("/member/modify/{id}")
    public String modifyMember(Model model, @PathVariable("id") String id){
        Member member = memberService.getMember(id);
        model.addAttribute("member", member);
        return "member/member-modify";
    }

    @PostMapping("/member/modify")
    public String modifyMemberPost(Model model,Member member){
        memberService.updateMember(member);
        return "redirect:/members";
    }

    @PostMapping("/member/delete")
    public String deleteMember(Model model,Member member){
        memberService.deleteMember(member);
        return "redirect:/members";
    }

    @GetMapping("/member/form")
    public String formGet(Model model){
        return "member/member-form";
    }
    @PostMapping("/member/form")
    public String formPost(Model model,Member member){
        memberService.insertMember(member);
        return "redirect:/members";
    }


}
