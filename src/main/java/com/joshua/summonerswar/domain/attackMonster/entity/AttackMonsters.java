package com.joshua.summonerswar.domain.attackMonster.entity;

import com.joshua.summonerswar.domain.attackMonster.dto.request.AttackMonsterRequestDto;
import com.joshua.summonerswar.domain.defMonster.entity.DefMonsters;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class AttackMonsters extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String deckName;

    private String detailInfo;

    @ManyToMany (mappedBy = "attackMonsters")
    private List<DefMonsters> defMonsters;

    public static AttackMonsters toEntity (final AttackMonsterRequestDto.@NotNull Register request) {
        return AttackMonsters.builder()
                .deckName(request.getDeckName())
                .detailInfo(request.getDetailInfo())
                .build();
    }
}
