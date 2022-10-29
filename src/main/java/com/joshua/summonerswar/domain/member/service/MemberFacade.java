package com.joshua.summonerswar.domain.member.service;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.dto.response.MemberResponseDto;
import com.joshua.summonerswar.domain.member.service.core.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberService memberService;

    /**
     * 회원가입 Facade
     *
     * @param request : Controller 에서 전달 받은 가입 정보
     * @return
     */
    public MemberResponseDto.MemberInfo join (final MemberRequestDto.@NotNull Join request) {
        return MemberResponseDto.MemberInfo.toDtoFromEntity(
                memberService.join(request));
    }

    public MemberResponseDto.MemberInfo update (final @NotNull Long id,
                                                final MemberRequestDto.@NotNull Update request) {
        return MemberResponseDto.MemberInfo.toDtoFromEntity(memberService.update(id, request));

    }

}
