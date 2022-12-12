package com.joshua.summonerswar.domain.reply.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.domain.reply.dto.response.ReplyResponseDto;
import com.joshua.summonerswar.domain.reply.entity.Reply;
import com.joshua.summonerswar.domain.reply.service.core.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyFacade {

    private final ReplyService replyService;

    @Transactional
    public ReplyResponseDto register (final @NotNull Member member,
                                      final @NotNull ReplyRequestDto.Register request,
                                      String parentId) {

        // 첫 댓글을 다는 경우 (parentId 가 없는 경우)
        if (!StringUtils.hasText(parentId)) {

            Reply parentReply = replyService.findById(Long.valueOf(parentId)); // find parent reply
            Reply reply = replyService.registerChild(member, request, parentReply); //register child reply (itself)

            parentReply.getSubReplyList().add(reply);
            return ReplyResponseDto.toDtoFromEntity(reply);
        }

        return ReplyResponseDto.toDtoFromEntity(replyService.register(member, request));
    }

    @Transactional
    public ReplyResponseDto update (final @NotNull Member member,
                                    final @NotNull Long replyId,
                                    final @NotNull ReplyRequestDto.Update request) {

        Reply reply = replyService.findById(replyId);

        // reply owner, changer not equal
        if (reply.getMember() != member) {
            throw new IllegalArgumentException("Member is not matched");
        }

        return ReplyResponseDto.toDtoFromEntity(Reply.update(reply, request));
    }

    @Transactional
    public void delete(final @NotNull Member member,
                       final @NotNull Long id) {

        Reply reply = replyService.findById(id);

        if (reply.getMember() != member) {
            throw new IllegalArgumentException("Member is not matched");
        }

        replyService.delete(reply);
    }

}
