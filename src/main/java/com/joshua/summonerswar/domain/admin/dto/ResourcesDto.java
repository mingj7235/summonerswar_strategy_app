package com.joshua.summonerswar.domain.admin.dto;

import com.joshua.summonerswar.domain.admin.entity.Resources;
import com.joshua.summonerswar.domain.admin.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto {

    private String id;
    private String resourceName;
    private String httpMethod;
    private int orderNum;
    private String resourceType;
    private String roleName;
    private Set<Role> roleSet;

    public static ResourcesDto toDtoFromEntity(Resources resources) {
        return ResourcesDto.builder()
                .resourceName(resources.getResourceName())
                .httpMethod(resources.getHttpMethod())
                .orderNum(resources.getOrderNum())
                .resourceType(resources.getResourceType())
                .roleSet(resources.getRoleSet())
                .build();
    }
}
