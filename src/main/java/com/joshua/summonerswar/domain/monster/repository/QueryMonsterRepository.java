package com.joshua.summonerswar.domain.monster.repository;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface QueryMonsterRepository {

    List<Monster> searchByConditions (final MonsterRequestDto.@NotNull Search condition);

    List<MonsterResponseDto> findAllDtoList();


}
