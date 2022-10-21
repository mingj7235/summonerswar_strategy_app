package com.joshua.summonerswar.domain.monster.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonsterRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Register {

        private String name;

        private String attribute;

        private String leaderSkill;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Update {

        private String leaderSkill;
    }
}
