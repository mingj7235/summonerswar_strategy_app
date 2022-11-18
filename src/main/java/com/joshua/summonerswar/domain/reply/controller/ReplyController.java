package com.joshua.summonerswar.domain.reply.controller;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.domain.reply.dto.response.ReplyResponseDto;
import com.joshua.summonerswar.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping ("/replies")
    public ResponseEntity<ReplyResponseDto> register (Authentication authentication,
                                                      final @NotNull ReplyRequestDto.Register request) {

        Member member = (Member) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(replyService.register(member, request));
    }

    @PutMapping ("/replies")
    public ResponseEntity<ReplyResponseDto> update (Authentication authentication,
                                                    final @NotNull ReplyRequestDto.Update request) {
        return null;
    }
}
