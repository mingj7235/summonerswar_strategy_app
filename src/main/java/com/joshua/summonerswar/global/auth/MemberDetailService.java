package com.joshua.summonerswar.global.auth;

import com.joshua.summonerswar.domain.auth.Key.CacheKey;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Cacheable (value = CacheKey.USER, key = "#username", unless = "#result == null") //DB Caching
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsernameWithAuthority(username).orElseThrow(() -> new NoSuchElementException("없는 유저임"));

        return MemberDetails.of(member);
    }

}



