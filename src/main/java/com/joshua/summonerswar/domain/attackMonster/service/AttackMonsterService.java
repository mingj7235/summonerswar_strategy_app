package com.joshua.summonerswar.domain.attackMonster.service;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import com.joshua.summonerswar.domain.attackMonster.dto.response.AttackMonsterResponseDto;
import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import com.joshua.summonerswar.domain.attackMonster.repository.AttackMonsterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttackMonsterService {

    private final AttackMonsterRepository attackMonsterRepository;

    public AttackMonsterResponseDto register (final AttackMonsterRequestDto.@NotNull Register request) {

        return AttackMonsterResponseDto.from(attackMonsterRepository.save(AttackMonsters.toEntity(request)));
    }

    public Page<AttackMonsterResponseDto> find (final AttackMonsterRequestDto.@NotNull Find request,
                                                Pageable pageable) {
        return attackMonsterRepository.findByKeyword(request, pageable)
                .map(AttackMonsterResponseDto::from);
    }
}
