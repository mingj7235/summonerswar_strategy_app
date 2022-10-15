package com.joshua.summonerswar.domain.resource.repository;

import com.joshua.summonerswar.domain.resource.entity.Resources;

import java.util.List;

public interface QueryResourcesRepository {

    List<Resources> findAllResources();

}
