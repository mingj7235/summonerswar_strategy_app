package com.joshua.summonerswar.domain.siege.dto.response;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefDeckResponseDto {

    private String deckName;

    private String deckDescription;

    private Monster leaderMonster;

    private Monster secondMonster;

    private Monster thirdMonster;

    public static DefDeckResponseDto toDtoFromEntity(final @NotNull DefDeck entity) {
        return DefDeckResponseDto.builder()
                .deckName(entity.getDeckName())
                .deckDescription(entity.getDeckDescription())
                .build();
    }
}
