package com.joshua.summonerswar.domain.siege.controller.atk;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AtkDeckController {

    private final AtkDeckFacade atkDeckFacade;

    private final MonsterManagerFacade monsterManagerFacade;

    @GetMapping ("/atkDecks")
    public String viewList (Model model) {

        model.addAttribute("monsters", monsterManagerFacade.findAll());

        return "atk/list";
    }

    @GetMapping ("/atkDecks/register")
    public String viewRegister (Model model) {

        model.addAttribute("monsters", monsterManagerFacade.findAll());

        return "atk/register";
    }

    @GetMapping ("/atkDecks/{id}")
    public String viewDetail (Authentication authentication,
                              @PathVariable String id,
                              Model model) {

        Member member = (Member) authentication.getPrincipal();

        model
                .addAttribute("memberEmail", member.getEmail())
                .addAttribute("atkDecks", atkDeckFacade.findById(id));

        return "atk/detail";
    }




}
