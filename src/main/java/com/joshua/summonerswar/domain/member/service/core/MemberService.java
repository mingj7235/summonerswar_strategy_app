package com.joshua.summonerswar.domain.member.service.core;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member join(final MemberRequestDto.@NotNull Join request) {
        log.info("일반 유저 가입 : {}", request.getNickname());
        Member member = memberRepository.save(Member.toEntity(request));

        return Member.updatePassword(member, passwordEncoder.encode(request.getPassword()));
    }

    public Member update(final @NotNull Long id,
                         final MemberRequestDto.@NotNull Update request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found Member"));

        Member updatedMember = Member.updateInfo(member, request);

        if (StringUtils.hasText(request.getPassword())) {
            return Member.updatePassword(updatedMember, passwordEncoder.encode(request.getPassword()));
        }
        return updatedMember;
    }

    public String checkPassword (String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            return "fail";
        }
        return "ok";
    }
    public String checkEmailDuplication(final String email) {
        if (memberRepository.existsByEmail(email)) {
            return "fail";
        }

        return "ok";
    }

    public boolean existsByEmail(final @NotBlank String email) {
        return memberRepository.existsByEmail(email);
    }



}
