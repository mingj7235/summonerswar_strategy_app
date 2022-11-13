package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class DefDeckApiController {

    private final DefDecksFacade defDecksFacade;

    /**
     * Ajax - 검색 조건에 따른 방덱 조회
     *
     * @param request
     * @return
     */
    @PostMapping("/defDecks/search")
    public ResponseEntity<List<DefDeckResponseDto.Search>> search (final DefDeckRequestDto.@NotNull Search request) {

        return ResponseEntity.ok().body(defDecksFacade.search(request));
    }

    /**
     * Ajax - 방덱 저장
     *
     * @param authentication
     * @param request
     * @return
     */
    @PostMapping ("/defDecks")
    public ResponseEntity<String> register (Authentication authentication,
                                            final DefDeckRequestDto.@NotNull Register request) {

        Member member = (Member) authentication.getPrincipal();

        if (defDecksFacade.register(member, request) != null) {
            return ResponseEntity.ok("ok");
        }

        return ResponseEntity.ok("fail");
    }

    @PatchMapping("/defDecks/{id}")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> update (Authentication authentication,
                                                      final @NotNull @PathVariable String id,
                                                      final @NotNull DefDeckRequestDto.Update request) {

        return ResponseEntity.ok()
                .body(defDecksFacade.update(id, authentication.getName(), request));
    }

    @DeleteMapping("/defDecks/{id}")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> delete (Authentication authentication,
                                                      final @NotNull @PathVariable String id) {

        defDecksFacade.delete(authentication.getName(), id);

        return ResponseEntity.noContent()
                .build();
    }



}
