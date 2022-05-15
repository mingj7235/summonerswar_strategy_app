package com.joshua.summonerswar.global.auth;

import com.joshua.summonerswar.domain.auth.Key.CacheKey;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))); //정석적으로 사용하는 방법은 아니지만 이렇게 사용하는게 간편하기도함
        // 왜이렇게 진행하는가? password를 인코딩된 것만 접근 가능하기때문에 (plain password에 접근할 수 없다.)
        // 정석적인 방법을 하려면 plain password 를 알아야한다.
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);

    @Override
    @Cacheable (value = CacheKey.USER, key = "#username", unless = "#result == null") //DB Caching
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsernameWithAuthority(username).orElseThrow(() -> new NoSuchElementException("없는 유저임"));

        return MemberDetails.of(member);
    }

}



