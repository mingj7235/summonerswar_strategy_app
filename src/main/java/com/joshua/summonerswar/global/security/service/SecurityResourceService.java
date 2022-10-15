package com.joshua.summonerswar.global.security.service;

import com.joshua.summonerswar.domain.resource.entity.Resources;
import com.joshua.summonerswar.domain.resource.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityResourceService {

    /**
     * DB 에서 자원 정보를 가져오고, 그 자원에 Role 을 등록하는 서비스.
     * 이 서비스는 UrlREsourcesMapFactoryBean 에서 사용된다.
     *
     * RequestMatcher 를 요청 자원
     * List<ConfigAttribute> 는 해당 자원에 매핑되는 권한들을 의미한다.</ConfigAttribute>
     */

    private final ResourcesRepository resourcesRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();

        List<Resources> resourcesList = resourcesRepository.findAllResources();

        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            re.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
                result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributeList);
            });
        });

        return result;
    }

}
