package com.joshua.summonerswar.domain.siege.service.core;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.repository.DefDeckRepository;
import com.joshua.summonerswar.domain.siege.service.core.relation.RelMonsterDefDeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefDeckService {

    private final RelMonsterDefDeckService monsterDefDeckService;
    private final DefDeckRepository defDeckRepository;

    public DefDeck register(String makerName, DefDeckRequestDto.Register request) {

        return defDeckRepository.save(DefDeck.toEntityForRegister(makerName, request));
    }

    public List<DefDeck> findAll() {
        return defDeckRepository.findAll();
    }
}
