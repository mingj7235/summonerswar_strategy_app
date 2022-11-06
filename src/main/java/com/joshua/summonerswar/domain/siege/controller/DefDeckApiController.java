package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class DefDeckApiController {

    private final DefDecksFacade defDecksFacade;

    @PostMapping("/defDecks/search")
    public ResponseEntity<List<DefDeckResponseDto>> search (final DefDeckRequestDto.@NotNull Search request) {

        return ResponseEntity.ok().body(defDecksFacade.search(request));
    }

    @PostMapping ("/defDecks")
    public ResponseEntity<String> register (Authentication authentication,
                                            final DefDeckRequestDto.@NotNull Register request) {

        Member member = (Member) authentication.getPrincipal();

        if (defDecksFacade.register(member.getNickname(), request) != null) {
            return ResponseEntity.ok("ok");
        }

        return ResponseEntity.ok("fail");
    }

}
