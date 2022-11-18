package com.joshua.summonerswar.domain.reply.dto.response;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.entity.Reply;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {

    private String boardName;

    private String replyMsg;

    private Integer depth;

    private Member member;
    public static ReplyResponseDto toDtoFromEntity(final Reply reply) {
        return ReplyResponseDto.builder()
                .boardName(reply.getBoardName())
                .replyMsg(reply.getReplyMsg())
                .depth(reply.getDepth())
                .member(reply.getMember())
                .build();
    }

}
