package com.joshua.summonerswar.domain.siege.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AtkDeckRequestDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Register {
        private String deckName;

        private String deckDescription;

        private Long leaderMonsterId;

        private Long secondMonsterId;

        private Long thirdMonsterId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Update {

        private String deckName;

        private String deckDescription;

        private Long leaderMonsterId;

        private Long secondMonsterId;

        private Long thirdMonsterId;
    }

}
