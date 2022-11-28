package com.joshua.summonerswar.domain.siege.controller.atk;

import com.joshua.summonerswar.domain.monster.service.MonsterManagerFacade;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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



}
