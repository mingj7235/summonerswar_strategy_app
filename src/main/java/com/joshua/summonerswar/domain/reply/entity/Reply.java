package com.joshua.summonerswar.domain.reply.entity;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String boardName;

    private String replyMsg;

    private Integer depth;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "parent_reply_id")
    private Reply parentReply;

    @OneToMany (mappedBy = "parentReply", cascade = CascadeType.ALL)
    private List<Reply> subReplyList = new ArrayList<>();

    public static Reply toEntity (final @NotNull ReplyRequestDto.Register request,
                                  final @NotNull Member member) {
        return Reply.builder()
                .boardName(request.getBoardName())
                .replyMsg(request.getReplyMsg())
                .depth(request.getDepth())
                .member(member)
                .build();
    }
}
