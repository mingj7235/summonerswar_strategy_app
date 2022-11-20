package com.joshua.summonerswar.domain.reply.entity;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.global.base.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

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

    public static Reply register(final @NotNull ReplyRequestDto.Register request,
                                 final @NotNull Member member) {
        return Reply.builder()
                .boardName(request.getBoardName())
                .replyMsg(request.getReplyMsg())
                .depth(0)
                .member(member)
                .build();
    }

    public static Reply registerChild(final ReplyRequestDto.Register request, final Member member, final Reply parentReply) {
        return Reply.builder()
                .boardName(request.getBoardName())
                .replyMsg(request.getReplyMsg())
                .depth(parentReply.getDepth() + 1)
                .member(member)
                .parentReply(parentReply)
                .build();
    }

    public static Reply update(final Reply reply, final ReplyRequestDto.Update request) {

        if (StringUtils.hasText(request.getReplyMsg()))
            reply.setReplyMsg(request.getReplyMsg());

        return reply;
    }

}
