package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.auth.token.TokenDto;
import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.dto.response.MemberResponseDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.service.MemberService;
import com.joshua.summonerswar.domain.member.validator.MemberJoinValidator;
import com.joshua.summonerswar.domain.member.validator.MemberLoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberJoinValidator memberJoinValidator;

    private final MemberLoginValidator memberLoginValidator;

    @InitBinder("join")
    public void initBinderJoin(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberJoinValidator);
    }

    @InitBinder ("login")
    public void initBinderLogin (WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberLoginValidator);
    }

    @GetMapping ("/")
    public String homeToLogin (Model model) {

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

    @PostMapping ("/member/join/admin")
    public String joinAdmin (final MemberRequestDto.@NotNull Join request) {
        memberService.joinAdmin(request);
        return "join admin completed";
    }

    @PostMapping ("/member/login")
    @ResponseBody
    public ResponseEntity<TokenDto> login (final MemberRequestDto.@NotNull Login request) {

        return ResponseEntity.ok()
                .body(memberService.login(request));
    }

    @GetMapping ("/member/{email}")
    @ResponseBody
    public MemberResponseDto.MemberInfo getMemberInfo (@PathVariable String email) {
        return memberService.getMemberInfo(email);
    }

    @PostMapping ("/reissue")
    public ResponseEntity<TokenDto> reissue (@RequestHeader ("RefreshToken") String refreshToken) {
        return ResponseEntity.ok()
                .body(memberService.reissue(refreshToken));
    }

    @PostMapping ("/logout")
    public void logout (@RequestHeader("Authorization") String accessToken,
                        @RequestHeader ("RefreshToken") String refreshToken) {
        String username = jwtTokenUtil.getUsername(resolveToken(accessToken));
        memberService.logout(TokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken (String accessToken) { //FIXME : 중복 메소드
        return accessToken.substring(7);
    }


}
