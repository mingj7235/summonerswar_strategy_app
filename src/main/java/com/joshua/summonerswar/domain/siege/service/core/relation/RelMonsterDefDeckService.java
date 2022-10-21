package com.joshua.summonerswar.domain.siege.service.core.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterDefDeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RelMonsterDefDeckService {

    private final RelMonsterDefDeckRepository repository;
    public void registerDefDeck(DefDeck defDeck, List<Monster> monsters) {
        monsters.forEach(monster -> {
            repository.save(MonsterDefDeck.toEntity(defDeck, monster));
        });
    }
}
