package com.joshua.summonerswar.domain.monster.repository;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
public class QueryMonsterRepositoryImpl implements QueryMonsterRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Monster> searchByConditions(final MonsterRequestDto.@NotNull Search condition) {

        return null;
    }

}
