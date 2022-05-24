package com.joshua.summonerswar.domain.attackMonster.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class AttackMonsterRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Register {
        private String deckName;

        private String detailInfo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Valid
    public static class Find {
        private String keyword;
    }
}
