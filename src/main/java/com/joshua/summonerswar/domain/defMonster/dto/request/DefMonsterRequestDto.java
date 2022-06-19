package com.joshua.summonerswar.domain.defMonster.dto.request;

import com.joshua.summonerswar.domain.attackMonster.entity.AttackMonsters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class DefMonsterRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Register {
        private String leaderMonster;

        private String otherMonster;

        private String detailInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Find {
        private String leaderMonster;

        private String keyword;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Update {
        private String detailInfo;

        private List<AttackMonsters> attackMonsters;
    }

}















