package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelMonsterAtkDeckRepository extends JpaRepository<MonsterAtkDeck, Long> {
}
