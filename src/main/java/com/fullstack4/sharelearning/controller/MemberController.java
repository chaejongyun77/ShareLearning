package com.fullstack4.sharelearning.controller;

import com.fullstack4.sharelearning.dto.MemberDTO;
import com.fullstack4.sharelearning.service.MemberServiceIf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceIf memberServiceIf;

    @GetMapping("/mypage")
    public void MypageGET(){



    }
    @PostMapping("/mypage")
    public String MypagePOST(Model model,
                          HttpServletRequest req,
                          MemberDTO memberDTO){
        memberDTO.setEmail(memberDTO.getEmail1()+memberDTO.getEmail2());
        memberDTO.setPhoneNumber(memberDTO.getNumber1() + memberDTO.getNumber2() + memberDTO.getNumber3());
        System.out.println("memberDTO name: " + memberDTO.getName());


        memberServiceIf.modify(memberDTO.getPwd(),memberDTO.getPhoneNumber(),memberDTO.getEmail(),memberDTO.getUser_id());

        HttpSession session = req.getSession();
        session.setAttribute("memberDTO",memberDTO);
        return "redirect:/member/mypage";

    }

}
