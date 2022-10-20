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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    /**
     * 몬스터 전체 리스트 화면
     */

    @GetMapping("/monsters")
    public String viewList (Model model) {
        return "monster/list";
    }

    /**
     * 몬스터 등록 화면
     */
    @GetMapping("/monsters/register")
    public String viewRegister (Model model) {
        return "monster/register";
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
                .body(monsterService.register(request, multipartFile));
    }

    /**
     * 몬스터 전체 목록 API
     */
    @PostMapping ("/monsters")
    @ResponseBody
    public ResponseEntity<List<MonsterResponseDto>> getList () {

        return ResponseEntity.ok()
                .body(monsterService.getList());
    }

}
