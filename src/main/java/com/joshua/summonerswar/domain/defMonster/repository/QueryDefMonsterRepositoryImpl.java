package com.joshua.summonerswar.domain.defMonster.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueryDefMonsterRepositoryImpl implements QueryDefMonsterRepository{

    private final JPAQueryFactory jpaQueryFactory;
}
