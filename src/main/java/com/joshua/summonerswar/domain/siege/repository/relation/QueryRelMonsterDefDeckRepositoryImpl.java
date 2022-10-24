package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.joshua.summonerswar.domain.siege.entity.relation.QMonsterDefDeck.monsterDefDeck;

@RequiredArgsConstructor
public class QueryRelMonsterDefDeckRepositoryImpl implements QueryRelMonsterDefDeckRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Monster> findMonsterIdListByDefDeckId(final Long defDeckId) {
        return jpaQueryFactory.select(monsterDefDeck.monster)
                .from(monsterDefDeck)
                .where(
                        monsterDefDeck.defDeck.id.eq(defDeckId)
                )
                .fetch();
    }

}
