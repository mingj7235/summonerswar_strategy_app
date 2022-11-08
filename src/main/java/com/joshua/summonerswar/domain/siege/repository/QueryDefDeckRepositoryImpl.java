package com.joshua.summonerswar.domain.siege.repository;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.dto.response.QDefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.dto.response.QDefDeckResponseDto_Search;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.joshua.summonerswar.domain.monster.entity.QMonster.monster;
import static com.joshua.summonerswar.domain.siege.entity.QDefDeck.defDeck;
import static com.joshua.summonerswar.domain.siege.entity.QAtkDeck.atkDeck;
import static com.joshua.summonerswar.domain.siege.entity.relation.QAtkDeckDefDeck.atkDeckDefDeck;
import static com.joshua.summonerswar.domain.siege.entity.relation.QMonsterDefDeck.monsterDefDeck;

@RequiredArgsConstructor
public class QueryDefDeckRepositoryImpl implements QueryDefDeckRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DefDeckResponseDto> search(final DefDeckRequestDto.Search request) {
        return null;
    }

    @Override
    public List<DefDeck> findAllByOptions(final DefDeckRequestDto.Search request) {
        return jpaQueryFactory.select(defDeck)
                .from(defDeck)
                .join(defDeck.monsterDefDecks, monsterDefDeck)
                .join(monsterDefDeck.monster, monster)
                .leftJoin(defDeck.atkDeckDefDecks, atkDeckDefDeck)
                .leftJoin(atkDeckDefDeck.atkDeck, atkDeck)
                .where(
                        deckNameLike(request.getDeckName()),
                        makerNickNameLike(request.getMakerName()),
                        monsterIdEq(request.getMonsterId()),
                        atkDeckIdEq(request.getAtkDeckId())
                )
                .groupBy(defDeck)
                .fetch();
    }

    @Override
    public List<DefDeckResponseDto.Search> findAllDtoByOptions(DefDeckRequestDto.Search request) {
        return null;
    }

    private BooleanExpression deckNameLike (final @NotBlank String deckName) {
        return StringUtils.hasText(deckName) ? defDeck.deckName.contains(deckName) : null;
    }

    private BooleanExpression makerNickNameLike (final @NotBlank String nickName) {
        return StringUtils.hasText(nickName) ? defDeck.makerNickName.contains(nickName) : null;
    }

    private BooleanExpression monsterIdEq (final @NotBlank String monsterId)  {
        return StringUtils.hasText(monsterId) ? monsterDefDeck.monster.id.eq(Long.valueOf(monsterId)) : null;
    }

    private BooleanExpression atkDeckIdEq (final @NotBlank String atkDeckId)  {
        return StringUtils.hasText(atkDeckId) ? atkDeckDefDeck.atkDeck.id.eq(Long.valueOf(atkDeckId)) : null;
    }
}
