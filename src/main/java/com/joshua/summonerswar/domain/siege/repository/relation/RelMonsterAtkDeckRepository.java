package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelMonsterAtkDeckRepository extends JpaRepository<MonsterAtkDeck, Long> {

    List<MonsterAtkDeck> findByAtkDeckId(Long atkDeckId);

}
