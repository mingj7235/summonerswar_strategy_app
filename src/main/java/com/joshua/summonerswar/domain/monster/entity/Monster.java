package com.joshua.summonerswar.domain.monster.entity;

import com.joshua.summonerswar.domain.monster.dto.request.MonsterRequestDto;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterAtkDeck;
import com.joshua.summonerswar.domain.siege.entity.relation.MonsterDefDeck;
import com.joshua.summonerswar.global.base.BaseTime;
import com.joshua.summonerswar.global.enums.Attribute;
import com.joshua.summonerswar.global.enums.LeaderSkill;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany (mappedBy = "monster")
    private List<MonsterAtkDeck> monsterAtkDecks = new ArrayList<>();

    @OneToMany (mappedBy = "monster")
    private List<MonsterDefDeck> monsterDefDecks = new ArrayList<>();

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
