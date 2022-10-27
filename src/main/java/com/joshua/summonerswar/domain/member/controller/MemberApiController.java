package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping ("/passwordCheck")
    public ResponseEntity<String> passwordCheck (final MemberRequestDto.@NotNull PasswordCheck request) {

        return ResponseEntity.ok().body(memberService.checkPassword(request.getPassword(), request.getCheckPassword()));
    }
}
