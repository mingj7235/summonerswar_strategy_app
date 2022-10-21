package com.joshua.summonerswar.domain.siege.dto.response;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    public static DefDeckResponseDto toDtoFromRegister(final @NotNull DefDeck entity,
                                                       final @NotNull List<Monster> monsters) {
        return DefDeckResponseDto.builder()
                .deckName(entity.getDeckName())
                .deckDescription(entity.getDeckDescription())
                .leaderMonster(monsters.get(0))
                .secondMonster(monsters.get(1))
                .thirdMonster(monsters.get(2))
                .build();
    }
}
