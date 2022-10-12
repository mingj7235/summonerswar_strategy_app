package com.joshua.summonerswar.global.security.service;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found Member"));

        if (member == null) {
            if (memberRepository.countByEmail(username) == 0) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
        }



        return null;
    }

}
