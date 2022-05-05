package com.joshua.summonerswar.domain.defMonster.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

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
        private String deckName;

        private String detailInfo;
    }
}
