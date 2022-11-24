package com.joshua.summonerswar.domain.siege.dto.response;

import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtkDeckResponseDto {

    private String id;

    private String deckName;

//    private Monster leaderMonster;
//
//    private Monster secondMonster;
//
//    private Monster thirdMonster;

    public static AtkDeckResponseDto toDtoFromEntity(final AtkDeck atkDeck) {
        return AtkDeckResponseDto.builder()
                .id(String.valueOf(atkDeck.getId()))
                .deckName(atkDeck.getDeckName())
//                .leaderMonster(at)
                .build();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Search {

        private Long id;

        private String deckName;

        private String deckDescription;

        private String makerName;

        private String makerEmail;

        private List<MonsterResponseDto> monsterResponseDtoList;

        public static AtkDeckResponseDto.Search toDtoFromEntity (final @NotNull AtkDeck atkDeck) {
            return AtkDeckResponseDto.Search.builder()
                    .id(atkDeck.getId())
                    .deckName(atkDeck.getDeckName())
                    .deckDescription(atkDeck.getDeckDescription())
                    .makerName(atkDeck.getMember().getNickname())
                    .makerEmail(atkDeck.getMember().getEmail())
                    .build();
        }
    }

}
