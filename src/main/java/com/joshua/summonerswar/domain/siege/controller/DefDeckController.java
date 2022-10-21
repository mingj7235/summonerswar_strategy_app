package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.DefDeckFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
     * 방덱을 등록하는 API
     *
     * @param request
     * @return
     */
    @PostMapping ("/register")
    @ResponseBody
    public ResponseEntity<DefDeckResponseDto> register (final @NotNull DefDeckRequestDto.Register request) {


        return null;
    }

}
