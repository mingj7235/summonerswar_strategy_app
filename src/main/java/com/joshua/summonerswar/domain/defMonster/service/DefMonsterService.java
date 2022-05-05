package com.joshua.summonerswar.domain.defMonster.service;

import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.dto.response.DefMonsterResponseDto;
import com.joshua.summonerswar.domain.defMonster.repository.DefMonsterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefMonsterService {
    private DefMonsterRepository defMonsterRepository;

    public DefMonsterResponseDto register(final DefMonsterRequestDto request) {


    }

}
