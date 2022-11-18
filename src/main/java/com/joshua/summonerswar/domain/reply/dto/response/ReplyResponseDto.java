package com.joshua.summonerswar.domain.reply.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyResponseDto {

    private String boardName;

    private String replyMsg;

    private Integer depth;

}
