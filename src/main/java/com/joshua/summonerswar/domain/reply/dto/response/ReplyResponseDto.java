package com.joshua.summonerswar.domain.reply.dto.response;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.entity.Reply;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {

    private Long id;

    private String boardName;

    private String replyMsg;

    private Integer depth;

    private Member member;

    private ReplyResponseDto parentReply;

    private List<ReplyResponseDto> children = new ArrayList<>();
    public static ReplyResponseDto toDtoFromEntity(final Reply entity) {
        ReplyResponseDto replyResponseDto = ReplyResponseDto.builder()
                .id(entity.getId())
                .boardName(entity.getBoardName())
                .replyMsg(entity.getReplyMsg())
                .depth(entity.getDepth())
                .member(entity.getMember())
                .build();

        Reply reply = entity.getParentReply() == null ?
                new Reply() :
                entity.getParentReply();

        replyResponseDto.setParentReply(ReplyResponseDto.toDtoFromEntity(reply));

        // 자식 노드 설정
        List<ReplyResponseDto> subReplyDTO = entity.getSubReplyList() == null ?
                null :
                entity.getSubReplyList().stream().map(ReplyResponseDto::toDtoFromEntity).collect(Collectors.toList());
        replyResponseDto.setChildren(subReplyDTO);

        return replyResponseDto;
    }

}
