package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.DefDeckFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
@RequestMapping ("/def")
@RequiredArgsConstructor
public class DefDeckController {

    private final DefDeckFacade defDeckFacade;

    /**
     * 방덱 목록 불러오기
     */

    @GetMapping ("/monsters")
    public String viewList (Model model) {

        model.addAttribute("monsters", defDeckFacade.findAll());

        return "def/list";

    }

    /**
     * 방덱을 등록하는 API
     *
     * @param request
     * @return
     */
    @PostMapping ("/register")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> register (Authentication authentication,
                                                        final @NotNull DefDeckRequestDto.Register request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(defDeckFacade.register(authentication.getName(), request));

    }


}
