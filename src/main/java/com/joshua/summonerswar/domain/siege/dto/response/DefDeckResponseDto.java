package com.joshua.summonerswar.domain.siege.dto.response;

import com.joshua.summonerswar.domain.monster.dto.response.MonsterResponseDto;
import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.joshua.summonerswar.domain.siege.entity.DefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.AtkDeckDefDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefDeckResponseDto {

    private Long id;
    private String deckName;

    private String deckDescription;

    private Monster leaderMonster;

    private Monster secondMonster;

    private Monster thirdMonster;

    private String makerName;

    public static DefDeckResponseDto toDtoFromRegister(final @NotNull DefDeck entity,
                                                       final @NotNull List<Monster> monsters) {
        return DefDeckResponseDto.builder()
                .id(entity.getId())
                .deckName(entity.getDeckName())
                .deckDescription(entity.getDeckDescription())
                .leaderMonster(monsters.get(0))
                .secondMonster(monsters.get(1))
                .thirdMonster(monsters.get(2))
                .build();
    }

    public static DefDeckResponseDto toDtoFromEntity(final @NotNull DefDeck entity) {
        return DefDeckResponseDto.builder()

                .build();
    }

    @QueryProjection
    public DefDeckResponseDto(final String deckName, final String deckDescription, final Monster leaderMonster, final Monster secondMonster, final Monster thirdMonster, final String makerName) {
        this.deckName = deckName;
        this.deckDescription = deckDescription;
        this.leaderMonster = leaderMonster;
        this.secondMonster = secondMonster;
        this.thirdMonster = thirdMonster;
        this.makerName = makerName;
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

        public static Search toDtoFromEntity (final @NotNull DefDeck defDeck) {
            return Search.builder()
                    .id(defDeck.getId())
                    .deckName(defDeck.getDeckName())
                    .deckDescription(defDeck.getDeckDescription())
                    .makerName(defDeck.getMember().getNickname())
                    .makerEmail(defDeck.getMember().getEmail())
                    .build();
        }
    }

}
