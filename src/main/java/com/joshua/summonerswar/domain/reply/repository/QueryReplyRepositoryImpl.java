package com.joshua.summonerswar.domain.reply.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueryReplyRepositoryImpl implements QueryReplyRepository{

    private final JPAQueryFactory  jpaQueryFactory;

}
