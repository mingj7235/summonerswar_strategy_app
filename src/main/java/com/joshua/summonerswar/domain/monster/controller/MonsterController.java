package com.joshua.summonerswar.domain.monster.controller;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterManagerFacade monsterManagerFacade;

    /**
     * 몬스터 전체 리스트 화면
     */

    @GetMapping("/monsters")
    public String viewList (Model model) {

        model.addAttribute("monsters", monsterManagerFacade.getList());
        return "monster/list";
    }

    /**
     * 몬스터 등록 화면
     */
    @GetMapping("/monsters/register")
    public String viewRegister (Model model) {

        model.addAttribute("monster", new MonsterRequestDto.Register());
        return "monster/register";
    }

    /**
     * 몬스터 상세 조회 화면
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/monsters/{id}")
    public String viewDetail (@PathVariable String id,
                              Model model) {

        model.addAttribute("monster", monsterManagerFacade.findById(id));
        return "monster/detail";
    }

    /**
     * 몬스터 전체 목록 API
     */
    @PostMapping ("/monsters")
    @ResponseBody
    public ResponseEntity<List<MonsterResponseDto>> getList () {

        return ResponseEntity.ok()
                .body(monsterManagerFacade.getList());
    }

    /**
     * 몬스터 등록 API
     *
     * @param request
     * @return
     */
    @PostMapping ("/monsters/register")
    @ResponseBody
    public ResponseEntity<MonsterResponseDto> register (@RequestPart(name = "file", required = false) MultipartFile multipartFile,
                                                        final @RequestPart (name = "request") @NotNull MonsterRequestDto.Register request) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monsterManagerFacade.register(multipartFile, request));
    }

    /**
     * 몬스터 정보 수정 API
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping ("/monsters/update/{id}")
    @ResponseBody
    public ResponseEntity<MonsterResponseDto> update (@PathVariable String id,
                                                      final @RequestPart(name = "file", required = false) MultipartFile multipartFile,
                                                      final @RequestPart (name = "request") @NotNull MonsterRequestDto.Update request) throws IOException {

        return ResponseEntity.ok()
                .body(monsterManagerFacade.update(id, multipartFile, request));
    }

}
