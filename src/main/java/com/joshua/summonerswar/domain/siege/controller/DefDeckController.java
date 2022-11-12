package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DefDeckController {
    private final DefDecksFacade defDecksFacade;
    private final AtkDeckFacade atkDeckFacade;
    private final MonsterManagerFacade monsterManagerFacade;

    /**
     * 방덱 목록 불러오기
     */
    @GetMapping ("/defDecks")
    public String viewList (Model model) {
        model.addAttribute("monsters", monsterManagerFacade.findAll());
        model.addAttribute("atkDecks", atkDeckFacade.findAll());

        return "def/list";
    }

    /**
     * 방덱 등록 화면
     *
     * @param model
     * @return
     */
    @GetMapping ("/defDecks/register")
    public String viewRegister (Model model) {

        model.addAttribute("monsters", monsterManagerFacade.findAll());

        return "def/register";
    }

    /**
     * 방덱 세부 조회 화면
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/defDecks/{id}")
    public String viewDetail (@PathVariable String id,
                              Model model) {

        model.addAttribute("defDecks", defDecksFacade.findById(id));

        return "def/detail";
    }
}
