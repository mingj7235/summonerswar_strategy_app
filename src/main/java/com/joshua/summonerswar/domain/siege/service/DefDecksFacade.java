package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefDecksFacade {

    private final DefDeckService defDeckService;
    private final MonsterService monsterService;
    private final MemberService memberService;
    private final RelMonsterDefDeckService monsterDefDeckService;

    @Transactional (readOnly = true)
    public List<DefDeckResponseDto.Search> search (final DefDeckRequestDto.@NotNull Search request) {

        List<DefDeckResponseDto.Search> resultDtoList = new ArrayList<>();

        List<DefDeck> search = defDeckService.search(request);

        search.forEach(
                defDeck -> {
                    resultDtoList.add(getDefDeckResponseSearchDto(defDeck));
                }
        );

        return resultDtoList;
    }

    @Transactional (readOnly = true)
    public DefDeckResponseDto.Search findById (final @NotNull String id) {
        DefDeck defDeck = defDeckService.findById(id);

        return getDefDeckResponseSearchDto(defDeck);
    }

    public DefDeckResponseDto register(final @NotBlank Member member,
                                       final @NotNull DefDeckRequestDto.Register request) {

        DefDeck defDeck = defDeckService.register(member, request);

        List<Monster> monsters = getMonsters(request.getLeaderMonsterId(),
                                             request.getSecondMonsterId(),
                                             request.getThirdMonsterId());

        monsterDefDeckService.registerDefDeck(defDeck, monsters);

        return DefDeckResponseDto.toDtoFromRegister(defDeck, monsters);
    }

    public DefDeckResponseDto update(final @NotBlank String defDeckId,
                                     final @NotBlank String memberId,
                                     final @NotNull DefDeckRequestDto.Update request) {

        DefDeck defDeck = defDeckService.findById(defDeckId);

        List<Monster> monsterList = monsterDefDeckService.findMonsterListByDefDeckId(Long.valueOf(defDeckId));
        DefDeck.update(defDeck, request);

        return DefDeckResponseDto.toDtoFromRegister(defDeck, monsterList);
    }

    public void delete(final String deleteName, final String id) {


    }


    private List<Monster> getMonsters (final @NotNull Long leaderMonsterId,
                                       final @NotNull Long secondMonsterId,
                                       final @NotNull Long thirdMonsterId) {

        List<Monster> monsterList = new ArrayList<>();

        Monster leaderMonster = monsterService.findById(leaderMonsterId);
        Monster secondMonster = monsterService.findById(secondMonsterId);
        Monster thirdMonster = monsterService.findById(thirdMonsterId);

        monsterList.add(leaderMonster);
        monsterList.add(secondMonster);
        monsterList.add(thirdMonster);

        return monsterList;
    }

    /**
     * DefDeck 을 DefDeckResponseDto.Search 로 변경해주는 private method
     *
     * @param defDeck
     * @return
     */
    private DefDeckResponseDto.Search getDefDeckResponseSearchDto (final @NotNull DefDeck defDeck) {
        List<Monster> monsterList = defDeck.getMonsterDefDecks().stream().map(MonsterDefDeck::getMonster).collect(Collectors.toList());
        List<MonsterResponseDto> collect = monsterList.stream().map(MonsterResponseDto::toDtoFromEntity).collect(Collectors.toList());

        DefDeckResponseDto.Search defDeckResponseSearchDto = DefDeckResponseDto.Search.toDtoFromEntity(defDeck);
        defDeckResponseSearchDto.setMonsterResponseDtoList(collect);

        return defDeckResponseSearchDto;
    }

    public void updatable(final Member member, final String defDeckId) {

        DefDeck defDeck = defDeckService.findById(defDeckId);

        if (!defDeck.getMember().equals(member)) {
            throw new IllegalArgumentException("member is not match");
        }
    }

}
