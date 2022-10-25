package com.joshua.summonerswar.domain.member.service;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    private void checkPassword (String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    public boolean existsByEmail(final @NotBlank String email) {
        return memberRepository.existsByEmail(email);
    }

}
