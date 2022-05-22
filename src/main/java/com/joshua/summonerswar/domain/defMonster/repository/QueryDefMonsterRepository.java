package com.joshua.summonerswar.domain.defMonster.repository;

import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface QueryDefMonsterRepository {

    Page<DefMonsters> findByKeyword (final @NotNull DefMonsterRequestDto.Find request,
                                     Pageable pageable);
}
