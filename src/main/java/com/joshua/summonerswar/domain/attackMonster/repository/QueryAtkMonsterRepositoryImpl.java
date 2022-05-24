package com.joshua.summonerswar.domain.attackMonster.repository;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.validation.constraints.NotNull;

import java.util.List;

import static com.joshua.summonerswar.domain.attackMonster.entity.QAttackMonsters.attackMonsters;
import static com.joshua.summonerswar.domain.defMonster.entity.QDefMonsters.defMonsters;

@RequiredArgsConstructor
public class QueryAtkMonsterRepositoryImpl implements QueryAtkMonsterRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AttackMonsters> findByKeyword(AttackMonsterRequestDto.@NotNull Find request, Pageable pageable) {
        List<AttackMonsters> result = jpaQueryFactory.selectFrom(attackMonsters)
                .where(
                        monsterLike(request.getKeyword()),
                        detailInfoLike(request.getKeyword())
                )
                .orderBy(defMonsters.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<AttackMonsters> countQuery = jpaQueryFactory
                .selectFrom(attackMonsters)
                .where(
                        monsterLike(request.getKeyword()),
                        detailInfoLike(request.getKeyword())
                );

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    private BooleanExpression monsterLike(String monster) {
        return !monster.isEmpty() ? attackMonsters.deckName.contains(monster) : null;
    }

    private BooleanExpression detailInfoLike(String keyword) {
        return !keyword.isEmpty() ? attackMonsters.detailInfo.contains(keyword) : null;
    }
}
