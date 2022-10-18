package com.joshua.summonerswar.domain.admin.service;

import com.joshua.summonerswar.domain.admin.entity.Resources;
import com.joshua.summonerswar.domain.admin.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Transactional(readOnly = true)
    public Resources getResources(long id) {
        return resourcesRepository.findById(id).orElse(new Resources());
    }

    @Transactional(readOnly = true)
    public List<Resources> getResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    public void createResources(Resources Resources) {
        resourcesRepository.save(Resources);
    }

    public void deleteResources(long id) {
        resourcesRepository.deleteById(id);
    }
}
