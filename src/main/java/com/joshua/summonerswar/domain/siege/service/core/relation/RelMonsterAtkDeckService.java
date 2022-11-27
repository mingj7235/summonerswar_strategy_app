package com.joshua.summonerswar.domain.siege.service.core.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterAtkDeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RelMonsterAtkDeckService {

    private final RelMonsterAtkDeckRepository relMonsterAtkDeckRepository;

    public void registerAtkDeck(AtkDeck atkDeck, List<Monster> monsters) {
        monsters.forEach(monster -> {
            relMonsterAtkDeckRepository.save(MonsterAtkDeck.toEntity(atkDeck, monster));
        });
    }

    public void updateAtkDeck(AtkDeck atkDeck, List<Monster> monsters) {

        deleteRelation(atkDeck);

        monsters.forEach(monster -> {
            relMonsterAtkDeckRepository.save(MonsterAtkDeck.toEntity(atkDeck, monster));
        });
    }

    private void deleteRelation (AtkDeck atkDeck) {
        List<MonsterAtkDeck> monsterDefDeckList = relMonsterAtkDeckRepository.findByAtkDeckId(atkDeck.getId());
        relMonsterAtkDeckRepository.deleteAllById(monsterDefDeckList.stream().map(MonsterAtkDeck::getId).collect(Collectors.toList()));
    }

}
