package com.joshua.summonerswar.domain.monster.service;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MonsterService {

    private final MonsterRepository monsterRepository;

    public MonsterResponseDto register(final @NotNull MonsterRequestDto.Register request) {

        Monster monster = monsterRepository.save(Monster.toEntity(request));

        return MonsterResponseDto.toDtoFromEntity(monster);

    }
}
