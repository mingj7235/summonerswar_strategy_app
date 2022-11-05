package com.joshua.summonerswar.domain.siege.controller;

import com.joshua.summonerswar.domain.siege.service.DefDecksFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping ("/v1")
@RequiredArgsConstructor
public class DefDeckApiController {

    private final DefDecksFacade defDecksFacade;

}
