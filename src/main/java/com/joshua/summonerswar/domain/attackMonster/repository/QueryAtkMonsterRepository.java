package com.joshua.summonerswar.domain.attackMonster.repository;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface QueryAtkMonsterRepository {

    Page<AttackMonsters> findByKeyword (final AttackMonsterRequestDto.@NotNull Find request,
                                        Pageable pageable);
}
