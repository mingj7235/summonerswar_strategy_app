package com.joshua.summonerswar.global.auth;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        log.info("/auth/loginProc 요청 들어옴");
        Member member = memberRepository.findByEmail(email);

        MemberDetails memberDetails = null;


        if (member != null) {
            memberDetails = new MemberDetails();
            memberDetails.setMember(member);
        } else {
            throw new UsernameNotFoundException("not found 'username' ");
        }

        return memberDetails;
    }

}



