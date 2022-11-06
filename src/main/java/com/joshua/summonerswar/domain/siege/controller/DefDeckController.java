package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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

        model.addAttribute("defDecks", defDecksFacade.findAll());
        model.addAttribute("monsters", monsterManagerFacade.findAll());
        model.addAttribute("atkDecks", atkDeckFacade.findAll());

        return "def/list";

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

        model.addAttribute("monster", defDecksFacade.findById(id));

        return "def/detail";

    }

    /**
     * 방덱 등록 화면
     *
     * @param model
     * @return
     */
    @GetMapping ("/defDecks/register")
    public String viewRegister (Model model) {

        model.addAttribute("monsters", defDecksFacade.findAll());

        return "def/register";

    }

    /**
     * 방덱을 등록하는 API
     *
     * @param request
     * @return
     */
    @PostMapping ("/defDecks")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> register (Authentication authentication,
                                                        final @NotNull DefDeckRequestDto.Register request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(defDecksFacade.register(authentication.getName(), request));

    }

    @PatchMapping("/defDecks/{id}")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> update (Authentication authentication,
                                                      final @NotNull @PathVariable String id,
                                                      final @NotNull DefDeckRequestDto.Update request) {

        return ResponseEntity.ok()
                .body(defDecksFacade.update(authentication.getName(), id, request));
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
