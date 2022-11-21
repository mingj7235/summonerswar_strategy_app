package com.joshua.summonerswar.domain.siege.controller.atk;

import com.joshua.summonerswar.domain.siege.service.AtkDeckFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AtkDeckController {

    private final AtkDeckFacade atkDeckFacade;


}
