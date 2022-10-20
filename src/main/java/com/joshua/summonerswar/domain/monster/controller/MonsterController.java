package com.joshua.summonerswar.domain.monster.controller;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.service.MonsterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping ("/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    /**
     * 몬스터 전체 리스트 화면
     */

    @GetMapping("/list")
    public String viewList (Model model) {
        return "monster/list";
    }

    /**
     * 몬스터 등록 화면
     */
    @GetMapping("/register")
    public String viewRegister (Model model) {
        return "monster/register";
    }

    /**
     * 몬스터 등록 API
     *
     * @param request
     * @return
     */
    @PostMapping ("/register")
    public ResponseEntity<MonsterResponseDto> register (final @NotNull MonsterRequestDto.Register request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monsterService.register(request));
    }

}
