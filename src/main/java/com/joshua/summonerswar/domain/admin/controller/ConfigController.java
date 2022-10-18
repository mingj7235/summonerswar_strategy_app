package com.joshua.summonerswar.domain.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ConfigController {

    @GetMapping("/config")
    public String config() {
        return "admin/config";
    }
}
