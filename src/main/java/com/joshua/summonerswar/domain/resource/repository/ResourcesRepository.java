package com.joshua.summonerswar.domain.resource.repository;

import com.joshua.summonerswar.domain.resource.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, Long>, QueryResourcesRepository {

    Resources findByResourceNameAndHttpMethod (String resourceName, String httpMethod);

}
