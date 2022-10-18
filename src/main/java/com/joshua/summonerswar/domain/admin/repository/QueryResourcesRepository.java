package com.joshua.summonerswar.domain.admin.repository;

import com.joshua.summonerswar.domain.admin.entity.Resources;

import java.util.List;

public interface QueryResourcesRepository {

    List<Resources> findAllResources();

}
