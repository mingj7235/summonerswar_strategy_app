package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.service.core.AtkDeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AtkDeckFacade {

    private final AtkDeckService atkDeckService;

    private final MonsterService monsterService;

    @Transactional (readOnly = true)
    public List<AtkDeckResponseDto> findAll () {
        return atkDeckService.findAll()
                .stream()
                .map(AtkDeckResponseDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }
}
