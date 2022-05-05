package com.joshua.summonerswar.domain.defMonster.controller;

import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.service.DefMonsterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DefMonsterController {

    private final DefMonsterService defMonsterService;

    @PostMapping ("/def/register")
    public ResponseEntity<DefMonsterResponseDto> register (final DefMonsterRequestDto.@NotNull Register request) {
        return ResponseEntity.ok()
                .body(defMonsterService.register(request));
    }
}
