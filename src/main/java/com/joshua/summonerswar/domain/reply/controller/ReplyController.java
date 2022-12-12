package com.joshua.summonerswar.domain.reply.controller;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.domain.reply.dto.response.ReplyResponseDto;
import com.joshua.summonerswar.domain.reply.service.ReplyFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyFacade replyFacade;
    @PostMapping ("/replies")
    public ResponseEntity<ReplyResponseDto> register (Authentication authentication,
                                                      @RequestParam(required = false) String parentId,
                                                      final @NotNull ReplyRequestDto.Register request) {

        Member member = (Member) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(replyFacade.register(member, request, parentId));
    }

    @PutMapping ("/replies/{id}")
    public ResponseEntity<ReplyResponseDto> update (Authentication authentication,
                                                    final @NotNull ReplyRequestDto.Update request,
                                                    final @PathVariable Long id) {

        Member member = (Member) authentication.getPrincipal();

        return ResponseEntity.ok()
                .body(replyFacade.update(member, id, request));
    }

    @DeleteMapping ("/replies/{id}")
    public ResponseEntity<?> delete (Authentication authentication,
                                     final @PathVariable Long id) {

        Member member = (Member) authentication.getPrincipal();

        replyFacade.delete(member, id);

        return ResponseEntity.noContent().build();
    }
}
