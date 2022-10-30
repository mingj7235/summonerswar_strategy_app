package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping ("/join")
    public String join (Model model) {

        model.addAttribute(new MemberRequestDto.Join());

        return "member/join";
    }

    @GetMapping ("/mypage")
    public String mypage (Authentication authentication,
                          Model model) {

        return "member/mypage";
    }

}
