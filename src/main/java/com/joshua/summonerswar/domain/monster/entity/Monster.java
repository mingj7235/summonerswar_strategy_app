package com.joshua.summonerswar.domain.monster.entity;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.global.base.BaseTime;
import com.joshua.summonerswar.global.enums.Attribute;
import com.joshua.summonerswar.global.enums.LeaderSkill;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter (AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Monster extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Attribute attribute;

    @Enumerated(EnumType.STRING)
    private LeaderSkill leaderSkill;

    private String photoPath;

    public static Monster toEntity(final @NotNull MonsterRequestDto.Register request,
                                   final @NotBlank String photoPath) {
        return Monster.builder()
                .name(request.getName())
                .attribute(Attribute.toEnumByName(request.getAttribute()))
                .leaderSkill(LeaderSkill.toEnumByCode(request.getLeaderSkill()))
                .photoPath(photoPath)
                .build();
    }

    public static Monster update(Monster monster, MonsterRequestDto.Update request, String photoPath) {

        if (StringUtils.hasText(request.getLeaderSkill()))
            monster.setLeaderSkill(LeaderSkill.toEnumByCode(request.getLeaderSkill()));

        if (StringUtils.hasText(photoPath))
            monster.setPhotoPath(photoPath);

        return monster;
    }
}
