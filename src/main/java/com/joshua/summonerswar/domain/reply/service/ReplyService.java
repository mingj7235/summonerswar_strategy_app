package com.joshua.summonerswar.domain.reply.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.reply.dto.request.ReplyRequestDto;
import com.joshua.summonerswar.domain.reply.dto.response.ReplyResponseDto;
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
    public ReplyResponseDto findById (final @NotNull Long id) {

        return ReplyResponseDto.toDtoFromEntity(
                replyRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("reply cannot found")));
    }

    public ReplyResponseDto register(final @NotNull Member member,
                                     final @NotNull ReplyRequestDto.Register request) {

        Reply reply = replyRepository.save(Reply.toEntity(request, member));

        return ReplyResponseDto.toDtoFromEntity(reply);
    }

    public ReplyResponseDto update (final @NotNull Member member,
                                    final @NotNull ReplyRequestDto.Update request) {
        return null;
    }

}
