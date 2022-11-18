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

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyFacade {

    private final ReplyService replyService;

    @Transactional
    public ReplyResponseDto register (final @NotNull Member member,
                                      final @NotNull ReplyRequestDto.Register request) {

        return ReplyResponseDto.toDtoFromEntity(replyService.register(member, request));
    }

    @Transactional
    public ReplyResponseDto update (final @NotNull Member member,
                                    final @NotNull Long replyId,
                                    final @NotNull ReplyRequestDto.Update request) {

        Reply reply = replyService.findById(replyId);
        return null;
    }

}
