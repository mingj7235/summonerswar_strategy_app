package com.joshua.summonerswar.domain.defMonster.entity;

import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import com.joshua.summonerswar.domain.defMonster.dto.request.DefMonsterRequestDto;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter (AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@SuperBuilder
@Entity
public class DefMonsters extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String leaderMonster;

    private String otherMonster;

    private String detailInfo;

    private String imgUrl;

    @ManyToMany
    private List<AttackMonsters> attackMonsters;

    public static DefMonsters toEntity (final DefMonsterRequestDto.@NotNull Register request) {
        return DefMonsters.builder()
                .leaderMonster(request.getLeaderMonster())
                .otherMonster(request.getOtherMonster())
                .detailInfo(request.getDetailInfo())
                .imgUrl(request.getImgUrl())
                .build();
    }

    public DefMonsters update (final DefMonsterRequestDto.@NotNull Update update) {
        if (StringUtils.hasText(update.getDetailInfo())) {
            this.setDetailInfo(update.getDetailInfo());
        }

        if (update.getAttackMonsters() != null) {
            this.setAttackMonsters(update.getAttackMonsters());
        }

        return this;
    }
}
