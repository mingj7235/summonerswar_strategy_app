package com.joshua.summonerswar.domain.siege.controller.def;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    public String viewList (Authentication authentication,
                            Model model) {

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
    public String viewDetail (Authentication authentication,
                              @PathVariable String id,
                              Model model) {

        Member member = (Member) authentication.getPrincipal();

        model.addAttribute("memberEmail", member.getEmail());
        model.addAttribute("defDecks", defDecksFacade.findById(id));

        return "def/detail";
    }

    /**
     * 방덱 수정 화면
     *
     * - 인증된 객체의 정보와 방덱을 등록한 유저의 정보를 확인하여 동일한 유저가 아니라면 예외를 발생 시킨다.
     *
     * @param authentication
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("defDecks/modify/{id}")
    public String viewUpdate (Authentication authentication,
                              @PathVariable String id,
                              Model model) {

        Member member = (Member) authentication.getPrincipal();
        defDecksFacade.updatable(member, id);

        model.addAttribute("defDecks", defDecksFacade.findById(id));
        model.addAttribute("monsters", monsterManagerFacade.findAll());


        return "def/modify";
    }

    /**
     * 방덱 수정 (본인이 쓴글 )
     *
     * 댓글 기능
     *
     * 공덱 등록 기능
     *
     * 방덱 삭제 기능 (admin 유저 or 만든유저 )
     */
}
