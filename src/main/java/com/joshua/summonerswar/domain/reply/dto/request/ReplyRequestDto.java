package com.joshua.summonerswar.domain.reply.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Register {

        private String boardName;

        private String replyMsg;

        private Integer depth;


    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Update {

        private String replyMsg;

    }

}
