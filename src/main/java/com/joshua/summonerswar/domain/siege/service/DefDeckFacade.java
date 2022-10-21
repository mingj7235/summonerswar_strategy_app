package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.service.core.DefDeckService;
import com.joshua.summonerswar.domain.siege.service.core.relation.RelMonsterDefDeckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefDeckFacade {

    private final DefDeckService defDeckService;
    private final MonsterService monsterService;
    private final RelMonsterDefDeckService monsterDefDeckService;

    public DefDeckResponseDto register(final @NotBlank String makerName,
                                       final @NotNull DefDeckRequestDto.Register request) {

        DefDeck defDeck = defDeckService.register(makerName, request);
        List<Monster> monsters = getMonsters(request);

        monsterDefDeckService.registerDefDeck(defDeck, monsters);

        return DefDeckResponseDto.toDtoFromEntity(defDeck);
    }



    private List<Monster> getMonsters (final @NotNull DefDeckRequestDto.Register request) {

        List<Monster> monsterList = new ArrayList<>();

        Monster leaderMonster = monsterService.findById(String.valueOf(request.getLeaderMonsterId()));
        Monster secondMonster = monsterService.findById(String.valueOf(request.getSecondMonsterId()));
        Monster thirdMonster = monsterService.findById(String.valueOf(request.getThirdMonsterId()));

        monsterList.add(leaderMonster);
        monsterList.add(secondMonster);
        monsterList.add(thirdMonster);

        return monsterList;
    }
}
