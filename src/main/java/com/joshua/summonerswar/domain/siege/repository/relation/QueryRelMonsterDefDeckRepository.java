package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;

import java.util.List;

public interface QueryRelMonsterDefDeckRepository {

    List<Monster> findMonsterIdListByDefDeckId(Long defDeckId);

}
