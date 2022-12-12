package com.joshua.summonerswar.domain.reply.service.core;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.domain.reply.entity.Reply;
import com.joshua.summonerswar.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional (readOnly = true)
    public Reply findById (final @NotNull Long id) {
        return replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("reply cannot found"));
    }

    public Reply register(final @NotNull Member member,
                                     final @NotNull ReplyRequestDto.Register request) {

        return replyRepository.save(Reply.register(request, member));
    }

    public Reply registerChild(final Member member, final ReplyRequestDto.Register request, final Reply parentReply) {

        return replyRepository.save(Reply.registerChild(request, member, parentReply));
    }

    public void delete(final @NotNull Reply reply) {

        replyRepository.delete(reply);
    }

}
