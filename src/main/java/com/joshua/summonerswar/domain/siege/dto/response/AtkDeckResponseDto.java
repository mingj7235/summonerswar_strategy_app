package com.joshua.summonerswar.domain.siege.dto.response;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.AtkDeck;
import lombok.*;

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

}
