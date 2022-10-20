package com.joshua.summonerswar.domain.monster.entity;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.global.enums.Attribute;
import com.joshua.summonerswar.global.enums.LeaderSkill;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter (AccessLevel.PRIVATE)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Attribute attribute;

    private LeaderSkill leaderSkill;

    private String photoPath;

    public static Monster toEntity(final @NotNull MonsterRequestDto.Register request) {
        return Monster.builder()
                .name(request.getName())
                .attribute(Attribute.toEnumByName(request.getAttribute()))
                .leaderSkill(LeaderSkill.toEnumByCode(request.getLeaderSkill()))
                .photoPath(request.getPhotoPath())
                .build();
    }
}
