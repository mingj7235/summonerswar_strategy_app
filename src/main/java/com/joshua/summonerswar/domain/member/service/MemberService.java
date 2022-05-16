package com.joshua.summonerswar.domain.member.service;

import com.joshua.summonerswar.domain.member.dto.request.MemberRequestDto;
import com.joshua.summonerswar.domain.member.dto.response.MemberResponseDto;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import com.joshua.summonerswar.global.auth.MemberDetails;
import com.joshua.summonerswar.global.util.EncodeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member join(final MemberRequestDto.@NotNull Join request) {
        log.info("일반 유저 가입 : {}", request.getNickname());
        return memberRepository.save(Member.toEntity(request));
    }

    public void login (final @NotNull Member member) {

        MemberDetails memberDetails = new MemberDetails();

        memberDetails.setMember(member);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                memberDetails,
                memberDetails.getPassword(),
                memberDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }

    private void checkPassword (String rawPassword, String encodedPassword) {
        if (EncodeUtils.isNotMatches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("없는 유저임"));

        MemberDetails memberDetails = null;

        if (member != null) {
            memberDetails = new MemberDetails();
            memberDetails.setMember(member);
        } else {
            throw new IllegalArgumentException("없는 유저");
        }

        return memberDetails;
    }



    public boolean existsByEmail(final @NotBlank String email) {
        return memberRepository.existsByEmail(email);
    }

}
