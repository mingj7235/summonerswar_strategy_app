package com.joshua.summonerswar.domain.siege.repository;

import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;

import java.util.List;

public interface QueryDefDeckRepository {

    List<DefDeckResponseDto> search(DefDeckRequestDto.Search request);

    List<DefDeck> findAllByOptions(DefDeckRequestDto.Search request);


}
