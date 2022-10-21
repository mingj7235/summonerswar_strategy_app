package com.joshua.summonerswar.domain.siege.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class DefDeckRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Register {

        private String deckName;

        private String deckDescription;

        private Long leaderMonsterId;

        private Long secondMonsterId;

        private Long thirdMonsterId;

    }
}
