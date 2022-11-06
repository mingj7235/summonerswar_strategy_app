package com.joshua.summonerswar.domain.monster.dto.response;

import com.joshua.summonerswar.domain.monster.entity.Monster;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MonsterResponseDto {

    private Long id;
    private String name;

    private String attribute;

    private String leaderSkill;

    private String photoPath;

    public static MonsterResponseDto toDtoFromEntity(final @NotNull Monster monster) {
        return MonsterResponseDto.builder()
                .id(monster.getId())
                .name(monster.getName())
                .attribute(monster.getAttribute().name())
                .leaderSkill(monster.getLeaderSkill().getDescription())
                .photoPath(monster.getPhotoPath())
                .build();
    }

    @QueryProjection
    public MonsterResponseDto(final String name, final String attribute, final String leaderSkill, final String photoPath) {
        this.name = name;
        this.attribute = attribute;
        this.leaderSkill = leaderSkill;
        this.photoPath = photoPath;
    }

}
