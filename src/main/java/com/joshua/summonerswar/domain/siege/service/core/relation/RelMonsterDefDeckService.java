package com.joshua.summonerswar.domain.siege.service.core.relation;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterDefDeckRepository;
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
public class RelMonsterDefDeckService {

    private final RelMonsterDefDeckRepository repository;

    public void registerDefDeck(DefDeck defDeck, List<Monster> monsters) {
        monsters.forEach(monster -> {
            repository.save(MonsterDefDeck.toEntity(defDeck, monster));
        });
    }


    public List<Monster> findMonsterListByDefDeckId (final @NotNull Long defDeckId) {
        return repository.findMonsterIdListByDefDeckId(defDeckId);
    }

    public List<Monster> findMonsterListByRequest (final DefDeckRequestDto.@NotNull Search request) {
        return repository.findMonsterListByRequest(request);
    }

//    public List<DefDeckResponseDto> search(final DefDeckRequestDto.Search request) {
//        return repository.search(request);
//    }

}
