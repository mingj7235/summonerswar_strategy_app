package com.joshua.summonerswar.domain.admin.repository;

import com.joshua.summonerswar.domain.admin.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, Long>, QueryResourcesRepository {

    Resources findByResourceNameAndHttpMethod (String resourceName, String httpMethod);

}
