package com.joshua.summonerswar.domain.monster.dto.response;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MonsterResponseDto {

    private String name;

    private String attribute;

    private String leaderSkill;

    private String photoPath;

    public static MonsterResponseDto toDtoFromEntity(final @NotNull Monster monster) {
        return MonsterResponseDto.builder()
                .name(monster.getName())
                .attribute(monster.getAttribute().name())
                .leaderSkill(monster.getLeaderSkill().getDescription())
                .photoPath(monster.getPhotoPath())
                .build();
    }
}
