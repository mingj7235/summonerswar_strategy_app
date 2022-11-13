package com.joshua.summonerswar.domain.siege.service.core;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.repository.DefDeckRepository;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterDefDeckRepository;
import com.joshua.summonerswar.domain.siege.service.core.relation.RelMonsterDefDeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefDeckService {

    private final DefDeckRepository defDeckRepository;

    @Transactional (readOnly = true)
    public List<DefDeck> findAll() {
        return defDeckRepository.findAll();
    }

    @Transactional (readOnly = true)
    public List<DefDeck> search(final DefDeckRequestDto.Search request) {
        return defDeckRepository.findAllByOptions(request);
    }

    @Transactional (readOnly = true)
    public DefDeck findById(final String defDeckId) {
        return defDeckRepository.findById(Long.valueOf(defDeckId))
                .orElseThrow(() -> new IllegalArgumentException("Not Found DefDeck"));
    }

    public DefDeck register(Member member, DefDeckRequestDto.Register request) {
        return defDeckRepository.save(DefDeck.toEntityForRegister(member, request));
    }




}
