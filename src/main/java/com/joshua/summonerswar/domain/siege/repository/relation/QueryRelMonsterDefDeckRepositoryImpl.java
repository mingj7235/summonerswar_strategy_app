package com.joshua.summonerswar.domain.siege.repository.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.dto.response.QDefDeckResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.joshua.summonerswar.domain.monster.entity.QMonster.monster;
import static com.joshua.summonerswar.domain.siege.entity.QDefDeck.defDeck;
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

    @Override
    public List<Monster> findMonsterListByRequest(final DefDeckRequestDto.Search request) {
        return jpaQueryFactory.select(monsterDefDeck.monster)
                .from(monsterDefDeck)
                .where(
                        findMonsterOnDefDeck(request.getMonsterId())
                )
                .fetch();
    }

//    @Override
//    public List<DefDeckResponseDto> search(final DefDeckRequestDto.Search request) {
//        return jpaQueryFactory.select(new QDefDeckResponseDto(
//                defDeck.deckName,
//                defDeck.deckDescription,
//                monsterDefDeck.monster
//
//        ))
//                .from(defDeck)
//                .join(defDeck.monsterDefDecks, monsterDefDeck)
//                .join(monsterDefDeck.monster, monster)
//                .where(
//                        findMonsterOnDefDeck(request.getMonsterId())
//                )
//                .fetch();
//    }

    private BooleanExpression findMonsterOnDefDeck (final @NotNull String monsterId) {
        return StringUtils.hasText(monsterId) ? monsterDefDeck.defDeck.id.eq(Long.valueOf(monsterId)) : null;
    }

}
