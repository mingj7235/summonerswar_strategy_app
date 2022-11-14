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

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Search {
        private String deckName;

        private String makerName;

        private String monsterId;

        private String atkDeckId;

    }

}
