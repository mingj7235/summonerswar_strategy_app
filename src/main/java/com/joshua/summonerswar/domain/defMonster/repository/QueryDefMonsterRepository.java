package com.joshua.summonerswar.domain.defMonster.repository;

import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

public interface QueryDefMonsterRepository {

    Page<DefMonsters> findByLeaderMonster (final @NotBlank String leaderMonster,
                                           Pageable pageable);

    Page<DefMonsters> findByKeyword (final @NotBlank String keyword,
                                     Pageable pageable);
}
