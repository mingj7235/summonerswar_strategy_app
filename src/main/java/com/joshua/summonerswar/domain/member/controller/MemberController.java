package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.service.MemberService;
import com.joshua.summonerswar.domain.member.validator.MemberJoinValidator;
import com.joshua.summonerswar.domain.member.validator.MemberLoginValidator;
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

    @GetMapping ("/")
    public String homeToLogin (Model model) {

        model.addAttribute(new MemberRequestDto.Login());

        return "member/login";
    }

    @GetMapping ("/member/login")
    public String login(Model model) {
        model.addAttribute(new MemberRequestDto.Login());

        return "member/login";
    }

    @GetMapping ("/member/join")
    public String join (Model model) {

        model.addAttribute(new MemberRequestDto.Join());

        return "member/join";
    }

    @PostMapping ("/member/join")
    public String joinProc (final MemberRequestDto.@NotNull Join request,
                            Errors errors) {

        if (errors.hasErrors())
            return "member/join";

        Member member = memberService.join(request);
        memberService.login(member);
        return "redirect:/";
    }

    @GetMapping ("/home")
    public String home () {
        return "index";
    }

    @GetMapping ("/errors/403")
    public String errors_403 () {
        return "/errors/403";
    }

}
