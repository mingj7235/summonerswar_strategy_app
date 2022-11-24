package com.joshua.summonerswar.domain.siege.service.core;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import com.joshua.summonerswar.domain.siege.repository.AtkDeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AtkDeckService {

    private final AtkDeckRepository atkDeckRepository;

    public List<AtkDeck> findAll() {
        return atkDeckRepository.findAll();
    }

    public AtkDeck register(final Member member, final AtkDeckRequestDto.Register request) {
        return atkDeckRepository.save(AtkDeck.toEntityForRegister(member, request));
    }

    @Transactional (readOnly = true)
    public AtkDeck findById(final String atkDeckId) {
        return atkDeckRepository.findById(Long.valueOf(atkDeckId))
                .orElseThrow(() -> new IllegalArgumentException("Not Found AtkDeck"));

    }

}
