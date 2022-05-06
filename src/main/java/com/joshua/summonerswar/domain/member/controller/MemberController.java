package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.auth.token.TokenDto;
import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.dto.response.MemberResponseDto;
import com.joshua.summonerswar.domain.member.service.MemberService;
import com.joshua.summonerswar.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
//@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final JwtTokenUtil jwtTokenUtil; //FIXME : util 로 빼서 static method 화 할지 말지 고민

    @GetMapping ("/")
    public String homeToLogin (Model model) {

        model.addAttribute(new MemberRequestDto.Login());

        return "member/login";
    }

    @GetMapping ("/health")
    public String healthCheck () {
        return "health";
    }

    @PostMapping ("/member/join")
    public String join (@RequestBody final MemberRequestDto.@NotNull Join request) {
        memberService.join(request);
        return "join user completed";
    }

    @PostMapping ("/member/join/admin")
    public String joinAdmin (final MemberRequestDto.@NotNull Join request) {
        memberService.joinAdmin(request);
        return "join admin completed";
    }

    @PostMapping ("/member/login")
    @ResponseBody
    public ResponseEntity<TokenDto> login (@RequestBody final MemberRequestDto.@NotNull Login request) {
//    public String login (final MemberRequestDto.@NotNull Login request) {

        return ResponseEntity.ok()
                .body(memberService.login(request));

//        memberService.login(request);
//        return "redirect:/member/login";
    }

    @GetMapping ("/member/{email}")
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


//
//    @PostMapping ("/member/update")
//    public String update (final MemberRequestDto.@NotNull Update request) {
//
//        return null;
//    }
}
