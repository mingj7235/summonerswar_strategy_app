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

    private String leaderMonster;

    private String otherMonster;

    private String detailInfo;

    private String imgUrl;

    public static DefMonsterResponseDto from(final @NotNull DefMonsters entity) {
        return DefMonsterResponseDto.builder()
                .leaderMonster(entity.getLeaderMonster())
                .otherMonster(entity.getOtherMonster())
                .detailInfo(entity.getDetailInfo())
                .imgUrl(entity.getImgUrl())
                .build();
    }
}
