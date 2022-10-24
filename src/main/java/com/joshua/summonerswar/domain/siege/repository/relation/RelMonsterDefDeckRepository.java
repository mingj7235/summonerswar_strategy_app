package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelMonsterDefDeckRepository extends JpaRepository<MonsterDefDeck, Long>, QueryRelMonsterDefDeckRepository {

    List<MonsterDefDeck> findByMonsterId (Long monsterId);

    List<MonsterDefDeck> findByDefDeckId (Long defDeck_id);

}
