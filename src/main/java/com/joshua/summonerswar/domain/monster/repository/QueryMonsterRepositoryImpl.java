package com.joshua.summonerswar.domain.monster.repository;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.dto.response.QMonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.global.enums.LeaderSkill;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<MonsterResponseDto> findAllDtoList() {
        return jpaQueryFactory.select(new QMonsterResponseDto(
                monster.name,
                monster.attribute.stringValue(),
                monster.leaderSkill.stringValue(),
                monster.photoPath
        ))
                .from(monster)
                .fetch();
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

        if (StringUtils.hasText(leaderSkill)) {
            String name = Objects.requireNonNull(LeaderSkill.toEnumByCode(leaderSkill)).name();
            return monster.leaderSkill.stringValue().eq(name);
        }

        return null;
    }

}
