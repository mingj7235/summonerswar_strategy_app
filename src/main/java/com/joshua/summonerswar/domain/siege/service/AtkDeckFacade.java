package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterAtkDeckRepository;
import com.joshua.summonerswar.domain.siege.service.core.AtkDeckService;
import com.joshua.summonerswar.domain.siege.service.core.relation.RelMonsterAtkDeckService;
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

    private final RelMonsterAtkDeckService relMonsterAtkDeckService;

    @Transactional (readOnly = true)
    public List<AtkDeckResponseDto> findAll () {
        return atkDeckService.findAll()
                .stream()
                .map(AtkDeckResponseDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }

    public AtkDeckResponseDto register(final Member member, final AtkDeckRequestDto.Register request) {

        return AtkDeckResponseDto.toDtoFromEntity(atkDeckService.register(member, request));
    }

}
