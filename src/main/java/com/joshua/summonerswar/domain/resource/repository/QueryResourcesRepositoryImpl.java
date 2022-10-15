package com.joshua.summonerswar.domain.resource.repository;

import com.joshua.summonerswar.domain.resource.entity.Resources;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.joshua.summonerswar.domain.resource.entity.QResources.resources;
import static com.joshua.summonerswar.domain.role.entity.QRole.role;

@RequiredArgsConstructor
public class QueryResourcesRepositoryImpl implements QueryResourcesRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Resources> findAllResources() {
        return jpaQueryFactory.select(resources)
                .from(resources)
                .join(resources.roleSet, role)
                .where(resources.resourceType.eq("url"))
                .orderBy(resources.orderNum.desc())
                .fetch();
    }

}
