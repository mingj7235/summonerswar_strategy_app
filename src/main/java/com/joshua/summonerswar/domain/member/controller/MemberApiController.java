package com.joshua.summonerswar.domain.member.controller;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping ("/duplication")
    public ResponseEntity<String> checkEmailDuplication (final @NotBlank @RequestParam String email) {
        return ResponseEntity.ok()
                .body(memberService.checkEmailDuplication(email));
    }

    @PostMapping ("/passwordCheck")
    public ResponseEntity<String> passwordCheck (final MemberRequestDto.@NotNull PasswordCheck request) {

        return ResponseEntity.ok().body(memberService.checkPassword(request.getPassword(), request.getCheckPassword()));
    }

    @PostMapping ("/join")
    public ResponseEntity<String> joinProc (final MemberRequestDto.@NotNull Join request) {

        if (memberService.join(request) != null) {
            return ResponseEntity.ok().body("ok");
        }

        return ResponseEntity.ok().body("fail");
    }
}
