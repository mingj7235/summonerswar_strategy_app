package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.MemberFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @GetMapping ("/join")
    public String join (Model model) {

        model.addAttribute(new MemberRequestDto.Join());

        return "member/join";
    }

    @GetMapping ("/member/mypage/{email}")
    public String mypage (Authentication authentication,
                          @PathVariable String email,
                          Model model) {

        if (!authentication.getName().equals(email)) {
            throw new IllegalArgumentException("본인이 아닙니다.");
        }

        model.addAttribute("member", memberFacade.findByEmail(email));

        return "member/mypage";
    }

}
