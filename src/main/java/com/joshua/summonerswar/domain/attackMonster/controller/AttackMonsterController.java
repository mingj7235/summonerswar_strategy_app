package com.joshua.summonerswar.domain.attackMonster.controller;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import com.joshua.summonerswar.domain.attackMonster.dto.response.AttackMonsterResponseDto;
import com.joshua.summonerswar.domain.attackMonster.service.AttackMonsterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
@Slf4j
@Controller
@RequiredArgsConstructor
public class AttackMonsterController {

    private final AttackMonsterService attackMonsterService;

    @GetMapping ("/atk/register")
    public String viewAtkRegister (Model model) {

        model.addAttribute(new AttackMonsterRequestDto.Register());

        return "atk/register";
    }

    @PostMapping ("/atk/register")
    @ResponseBody
    public String atkRegister (final AttackMonsterRequestDto.@NotNull Register request) {

        AttackMonsterResponseDto register = attackMonsterService.register(request);

        return "redirect:/atk/register";
    }

    @GetMapping ("/atk/find")
    public String viewAtkFind (Model model) {

        model.addAttribute(new AttackMonsterRequestDto.Find());

        return "atk/find";
    }

    @PostMapping ("/atk/find")
    @ResponseBody
    public ResponseEntity<Page<AttackMonsterResponseDto>> atkFind (final AttackMonsterRequestDto.@NotNull Find request,
                                                                   Pageable pageable) {
        return ResponseEntity.ok()
                .body(attackMonsterService.find(request, pageable));
    }
}
