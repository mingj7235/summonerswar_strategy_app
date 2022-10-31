package com.joshua.summonerswar.domain.monster.repository;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.joshua.summonerswar.domain.monster.entity.QMonster.monster;

@RequiredArgsConstructor
public class QueryMonsterRepositoryImpl implements QueryMonsterRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Monster> searchByConditions(final MonsterRequestDto.@NotNull Search condition) {

        return jpaQueryFactory.select(monster)
                .from(monster)
                .where(
                    monsterNameLike(condition.getMonsterName()),
                        attributeEq(condition.getAttribute()),
                        leaderSkillEq(condition.getLeaderSkill())
                )
                .fetch()
                ;
    }

    private BooleanExpression monsterNameLike (String monsterName) {
        return StringUtils.hasText(monsterName) ? monster.name.contains(monsterName) : null;
    }

//    private BooleanExpression keywordLike (String keyword) {
//        return StringUtils.hasText(keyword) ? monster.
//    }

    private BooleanExpression attributeEq (String attribute) {
        return StringUtils.hasText(attribute) ? monster.attribute.stringValue().eq(attribute) : null;
    }

    private BooleanExpression leaderSkillEq (String leaderSkill) {
        return StringUtils.hasText(leaderSkill) ? monster.leaderSkill.stringValue().eq(leaderSkill) : null;
    }

}
