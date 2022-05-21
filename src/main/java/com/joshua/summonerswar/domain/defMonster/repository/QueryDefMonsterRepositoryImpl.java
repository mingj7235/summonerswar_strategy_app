package com.joshua.summonerswar.domain.defMonster.repository;

import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.validation.constraints.NotBlank;

import java.util.List;

import static com.joshua.summonerswar.domain.defMonster.entity.QDefMonsters.defMonsters;

@RequiredArgsConstructor
public class QueryDefMonsterRepositoryImpl implements QueryDefMonsterRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<DefMonsters> findByLeaderMonster(final @NotBlank String leaderMonster,
                                                 Pageable pageable) {
        List<DefMonsters> result = jpaQueryFactory.selectFrom(defMonsters)
                .where(
                        leaderMonsterLike(leaderMonster)
                )
                .orderBy(defMonsters.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<DefMonsters> countQuery = jpaQueryFactory
                .selectFrom(defMonsters)
                .where(
                        leaderMonsterLike(leaderMonster)
                );

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<DefMonsters> findByKeyword(final String keyword, final Pageable pageable) {

        List<DefMonsters> result = jpaQueryFactory.selectFrom(defMonsters)
                .where(
                        keywordLike(keyword)
                )
                .orderBy(defMonsters.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<DefMonsters> countQuery = jpaQueryFactory
                .selectFrom(defMonsters)
                .where(
                        keywordLike(keyword)
                );

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    private BooleanExpression leaderMonsterLike (String leaderMonster) {
        return !leaderMonster.isEmpty() ? defMonsters.leaderMonster.contains(leaderMonster) : null;
    }

    private BooleanExpression keywordLike (String keyword) {
        return !keyword.isEmpty() ? defMonsters.leaderMonster.contains(keyword) :
                defMonsters.otherMonster.contains(keyword);
    }
}
