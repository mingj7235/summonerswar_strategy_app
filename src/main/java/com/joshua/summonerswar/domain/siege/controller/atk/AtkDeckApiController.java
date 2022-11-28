package com.joshua.summonerswar.domain.siege.controller.atk;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
public class AtkDeckApiController {

    private final AtkDeckFacade atkDeckFacade;

    @PostMapping ("/atkDecks")
    public ResponseEntity<String> register (Authentication authentication,
                                            final AtkDeckRequestDto.@NotNull Register request) {

        Member member = (Member) authentication.getPrincipal();

        if (atkDeckFacade.register(member, request) != null)
            return ResponseEntity.ok("ok");

        return ResponseEntity.ok("fail");

    }

    @PostMapping ("/atkDeck/search")
    public ResponseEntity<List<AtkDeckResponseDto.Search>> search (final AtkDeckRequestDto.@NotNull Search request) {
        return ResponseEntity.ok().body(atkDeckFacade.search(request));
    }

}
