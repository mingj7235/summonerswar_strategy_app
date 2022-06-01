package com.joshua.summonerswar.domain.attackMonster.dto.response;

import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttackMonsterResponseDto {

    private String deckName;

    private String detailInfo;

    public static AttackMonsterResponseDto from (final @NotNull AttackMonsters entity) {
        return AttackMonsterResponseDto.builder()
                .deckName(entity.getDeckName())
                .detailInfo(entity.getDetailInfo())
                .build();
    }

}
