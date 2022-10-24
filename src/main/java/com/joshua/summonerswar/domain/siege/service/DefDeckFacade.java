package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import com.joshua.summonerswar.domain.siege.dto.request.DefDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
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

    public List<DefDeckResponseDto> findAll() {

        /**
         * 1. DefDeck 불러 오기
         * 2. DefDeck 에 맞물려있는 MonsterDefDeck 불러오기 (byDefDeckId)
         * 3. 불러온 MonsterDefDeck을 통해서 MonsterList 뽑아오기
         * 4. DefDeckResponse 에 담기
         */


        List<DefDeck> defDeckList = defDeckService.findAll();

        List<Monster> monsterList = new ArrayList<>();

        defDeckList
                        .forEach(defDeck -> {
                            defDeck.getMonsterDefDecks().forEach(monsterDefDeck -> {
                                monsterList.add(monsterDefDeck.getMonster());
                            });
                        });

        return null;
    }

    public DefDeckResponseDto register(final @NotBlank String makerName,
                                       final @NotNull DefDeckRequestDto.Register request) {

        DefDeck defDeck = defDeckService.register(makerName, request);
        List<Monster> monsters = getMonsters(request);

        monsterDefDeckService.registerDefDeck(defDeck, monsters);

        return DefDeckResponseDto.toDtoFromRegister(defDeck, monsters);
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
