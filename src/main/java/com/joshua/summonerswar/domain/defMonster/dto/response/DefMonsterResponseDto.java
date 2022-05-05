package com.joshua.summonerswar.domain.defMonster.dto.response;

import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class DefMonsterResponseDto {

    private String deckName;

    private String detailInfo;

    public static DefMonsterResponseDto from(final @NotNull DefMonsters entity) {
        return DefMonsterResponseDto.builder()
                .deckName(entity.getDeckName())
                .detailInfo(entity.getDetailInfo())
                .build();
    }
}
