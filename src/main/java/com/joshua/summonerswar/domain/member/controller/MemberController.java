package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import com.joshua.summonerswar.domain.member.validator.MemberJoinValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberJoinValidator memberJoinValidator;

    @InitBinder("join")
    public void initBinderJoin(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberJoinValidator);
    }

    @GetMapping ("/join")
    public String join (Model model) {

        model.addAttribute(new MemberRequestDto.Join());

        return "member/join";
    }

    @PostMapping ("/join")
    public String joinProc (final MemberRequestDto.@NotNull Join request,
                            Errors errors) {

        if (errors.hasErrors())
            return "member/join";

        memberService.join(request);
        return "redirect:/";
    }

}
