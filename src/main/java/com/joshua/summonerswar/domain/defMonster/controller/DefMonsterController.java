package com.joshua.summonerswar.domain.defMonster.controller;

import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.service.DefMonsterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DefMonsterController {

    private final DefMonsterService defMonsterService;


    @GetMapping("/def/register")
    public String registerView (final @NotNull Model model) {

        model.addAttribute("");

        return "def/register";
    }

    @PostMapping ("/def/register")
    public ResponseEntity<DefMonsterResponseDto> register (final DefMonsterRequestDto.@NotNull Register request) {
        return ResponseEntity.ok()
                .body(defMonsterService.register(request));
    }

    @GetMapping ("/def/find")
    public String findView (final @NotNull Model model) {

        model.addAttribute(new DefMonsterRequestDto.Find());

        return "def/find";
    }

    @PostMapping ("/def/findByKeyword")
    public ResponseEntity<Page<DefMonsterResponseDto>> findByKeyword (final @NotNull DefMonsterRequestDto.Find request,
                                                                      Pageable pageable) {

        return ResponseEntity.ok()
                .body(defMonsterService.findByKeyword(request, pageable));
    }

}
