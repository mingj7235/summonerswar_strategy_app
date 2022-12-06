package com.joshua.summonerswar.domain.siege.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.monster.service.core.MonsterService;
import com.joshua.summonerswar.domain.siege.dto.request.AtkDeckRequestDto;
import com.joshua.summonerswar.domain.siege.dto.response.AtkDeckResponseDto;
import com.joshua.summonerswar.domain.siege.dto.response.DefDeckResponseDto;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.joshua.summonerswar.domain.siege.repository.relation.RelMonsterAtkDeckRepository;
import com.joshua.summonerswar.domain.siege.service.core.AtkDeckService;
import com.joshua.summonerswar.domain.siege.service.core.relation.RelMonsterAtkDeckService;
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
public class AtkDeckFacade {

    private final AtkDeckService atkDeckService;

    private final MonsterService monsterService;

    private final RelMonsterAtkDeckService relMonsterAtkDeckService;

    @Transactional (readOnly = true)
    public List<AtkDeckResponseDto> findAll () {
        return atkDeckService.findAll()
                .stream()
                .map(AtkDeckResponseDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Transactional (readOnly = true)
    public AtkDeckResponseDto.Search findById (final @NotNull String id) {
        AtkDeck atkDeck = atkDeckService.findById(id);

        return getAtkDeckResponseSearchDto(atkDeck);
    }

    public AtkDeckResponseDto register(final Member member, final AtkDeckRequestDto.Register request) {

        return AtkDeckResponseDto.toDtoFromEntity(atkDeckService.register(member, request));
    }

    public AtkDeckResponseDto update (final @NotBlank String atkDeckId,
                                      final @NotBlank String memberId,
                                      final @NotNull AtkDeckRequestDto.Update request) {

        AtkDeck atkDeck = atkDeckService.findById(atkDeckId);

        if (!atkDeck.getMember().getEmail().equals(memberId)) {
            throw new IllegalArgumentException("member not match");
        }

        List<Monster> monsterList = monsterService.getMonsters(request.getLeaderMonsterId(),
                                    request.getSecondMonsterId(),
                                    request.getThirdMonsterId());
        AtkDeck updateAtkDeck = AtkDeck.update(atkDeck, request);

        relMonsterAtkDeckService.updateAtkDeck(updateAtkDeck, monsterList);

        return AtkDeckResponseDto.toDtoFromRegister(updateAtkDeck, monsterList);
    }

    private AtkDeckResponseDto.Search getAtkDeckResponseSearchDto (final @NotNull AtkDeck atkDeck) {
        List<Monster> monsterList = atkDeck.getMonsterAtkDecks().stream().map(MonsterAtkDeck::getMonster).collect(Collectors.toList());
        List<MonsterResponseDto> collect = monsterList.stream().map(MonsterResponseDto::toDtoFromEntity).collect(Collectors.toList());

        AtkDeckResponseDto.Search atkDeckResponseSearchDto = AtkDeckResponseDto.Search.toDtoFromEntity(atkDeck);
        atkDeckResponseSearchDto.setMonsterResponseDtoList(collect);

        return atkDeckResponseSearchDto;
    }

    public List<AtkDeckResponseDto.Search> search(final AtkDeckRequestDto.Search request) {

        List<AtkDeckResponseDto.Search> resultDtoList = new ArrayList<>();

        List<AtkDeck> search = atkDeckService.search(request);

        search.forEach(
                atkDeck -> {
                    resultDtoList.add(getAtkDeckResponseSearchDto(atkDeck));
                }
        );

        return resultDtoList;
    }

}
