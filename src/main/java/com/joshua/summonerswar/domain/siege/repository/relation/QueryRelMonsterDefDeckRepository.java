package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;

import java.util.List;

public interface QueryRelMonsterDefDeckRepository {

    List<Monster> findMonsterIdListByDefDeckId(Long defDeckId);
    List<Monster> findMonsterListByRequest(DefDeckRequestDto.Search request);
//    List<DefDeckResponseDto> search(DefDeckRequestDto.Search request);


}
