package com.joshua.summonerswar.domain.attackMonster.controller;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AttackMonsterController {

    @GetMapping ("/atk/register")
    public String viewAtkRegister (Model model) {
        return "atk/register";
    }

    @PostMapping ("/atk/register")
    public String atkRegister (final AttackMonsterRequestDto.@NotNull Register register) {
        return "redirect:/atk/register";
    }
}
